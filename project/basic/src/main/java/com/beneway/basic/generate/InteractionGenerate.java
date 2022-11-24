package com.beneway.basic.generate;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class InteractionGenerate {

  /**
   * 数据源配置
   */
  private static DataSourceConfig.Builder DATA_SOURCE_CONFIG;
  /**
   * 当前数据库的所有表名
   */
  private static List<String> DEFAULT_TABLES;
  /**
   * 设置的参数
   */
  private static Map<String, String> DEFAULT_ARGUMENTS;

  /**
   * git的全局配置文件名
   */
  private static final String gitFileName = ".gitconfig";

  /**
   * 当前用户的操作系统
   */
  private static final String WINDOWS = "Windows";
  private static final String MAC = "Mac";

  /**
   * git的全局配置信息
   */
  private static Map<String, String> gitConfig;

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    generatorAll();
  }

  //读取yaml中的datasource信息
  private static void initDataSource() {
    InputStream yml = ClassUtil.getClassLoader().getResourceAsStream("application-basic-dev.yml");
    ObjectMapper mapper = new ObjectMapper();
    Dict load = YamlUtil.load(new InputStreamReader(yml));
    try {
      JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(load));
      JsonNode druid = jsonNode.get("spring").get("datasource").get("druid");
      String url = druid.get("url").asText();
      String username = druid.get("username").asText();
      String password = druid.get("password").asText();
      DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(url, username, password);
      if (DEFAULT_ARGUMENTS.get("include").equals("all")) {
        getTableNames(url, username, password);
      }
    } catch (IOException e) {
      System.out.println("获取数据失败");
      e.printStackTrace();
    }
  }

  // 自己输入的表名
  private static List<String> getTables(String tables) {
    return DEFAULT_TABLES = tables.equals("all") ? new ArrayList<>() : Arrays.asList(tables.split(","));
  }

  private static void getTableNames(String url, String username, String password) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception e) {
      System.out.print("Error loading Mysql Driver!");
      e.printStackTrace();
    }
    try {
      Connection connect = DriverManager.getConnection(url, username,password);
      ResultSet rs = connect.getMetaData().getTables(connect.getCatalog(), null, null, new String[]{"TABLE"});
      while (rs.next()) {
        DEFAULT_TABLES.add(rs.getString("TABLE_NAME"));
      }
    } catch (Exception e) {
      System.out.print("get data error!");
      e.printStackTrace();
    }
    filterTables();
  }

  private static void generatorInteraction() {
    DEFAULT_ARGUMENTS = new HashMap<>();
    getGitUserName();
    System.out.println("请输入表名，多个英文逗号分隔？所有输入 all");
    DEFAULT_ARGUMENTS.put("include", scanner.next());
    getTables(DEFAULT_ARGUMENTS.get("include"));
  }

  private static String moduleDir() {
    String outputDir = "%s\\project\\%s\\src\\main\\java\\";
    String dir = System.getProperty("user.dir");
    String module = DEFAULT_ARGUMENTS.get("module");
    String path = String.format(outputDir, dir, module).replace("\\", File.separator);
    return path;
  }

  // 过滤不参与生成的表
  private static void filterTables() {
    DEFAULT_TABLES.removeIf(s -> s.startsWith("access_"));
    DEFAULT_TABLES.removeIf(s -> s.startsWith("log_"));
    DEFAULT_TABLES.removeIf(s -> s.startsWith("sys_"));
  }

  public static void generatorAll() {
    try {
      initGitConfig();
      generatorInteraction();
      initDataSource();
      generator();
      generatorController();
    } finally {
      scanner.close();
    }
  }

  private static void generator() {
    DEFAULT_ARGUMENTS.put("module", "basic");
    DEFAULT_ARGUMENTS.put("parent", "com.beneway.basic");
    DEFAULT_TABLES.forEach(InteractionGenerate::singleExecute);
  }

  private static void generatorController() {
    DEFAULT_ARGUMENTS.put("module", "web");
    DEFAULT_ARGUMENTS.put("parent", "com.beneway.web.controller");
    DEFAULT_TABLES.forEach(InteractionGenerate::controller);
  }

  private static void singleExecute(String tableName) {
    FastAutoGenerator.create(DATA_SOURCE_CONFIG)
            // 不生成controller
            .templateConfig(builder -> builder
                    .disable(TemplateType.CONTROLLER)
                    .entity("/template/entity/entity.java.vm")
                    .mapper("/template/mapper/mapper.java.vm")
                    .service("/template/service/service.java.vm")
                    .serviceImpl("/template/service/impl/serviceImpl.java.vm")
                    .build())
            // 全局配置
            .globalConfig((scanner, builder) -> builder
                    .author(DEFAULT_ARGUMENTS.get("author"))
                    .outputDir(moduleDir())
                    .disableOpenDir()
                    .build())
            // 包配置
            .packageConfig((scanner, builder) -> builder
                    .parent(DEFAULT_ARGUMENTS.get("parent"))
                    .moduleName(tableName)
                    .entity("entity.po")
                    .mapper("dao")
                    .xml("mapper")
                    .build())
            // 策略配置
            .strategyConfig((scanner, builder) -> builder
                    .addInclude(tableName)
                    .serviceBuilder()
                    .superServiceClass("com.beneway.basic.mybatisplus.MyIService")
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl")
                    .entityBuilder()
                    .enableFileOverride()
                    .enableTableFieldAnnotation()
                    .enableLombok()
                    .enableChainModel()
                    .enableActiveRecord()
                    .idType(IdType.ASSIGN_UUID)
                    .mapperBuilder()
                    .superClass(BaseMapper.class)
                    .mapperAnnotation(Mapper.class)
                    .formatMapperFileName("%sDao")
                    .formatXmlFileName("%sMapper")
                    .build())
            .execute();
  }

  private static void controller(String tableName) {
    FastAutoGenerator.create(DATA_SOURCE_CONFIG)
            .templateConfig(builder -> builder
                    .disable(TemplateType.ENTITY,
                            TemplateType.SERVICE,
                            TemplateType.SERVICE_IMPL,
                            TemplateType.MAPPER,
                            TemplateType.XML)
                    .controller("/template/controller/controller.java.vm")
                    .build())
            // 全局配置
            .globalConfig((scanner, builder) -> builder
                    .author(DEFAULT_ARGUMENTS.get("author"))
                    .outputDir(moduleDir())
                    .disableOpenDir()
                    .build())
            // 包配置
            .packageConfig((scanner, builder) -> builder
                    .parent(DEFAULT_ARGUMENTS.get("parent"))
                    .controller(tableName)
                    .build())
            // 策略配置
            .strategyConfig((scanner, builder) -> builder
                    .addInclude(tableName)
                    .controllerBuilder()
                    .enableRestStyle()
                    .enableHyphenStyle()
                    .build())
            .execute();
  }

  private static void getGitUserName() {
    if (Objects.isNull(gitConfig)) {
      System.out.println("作者名称自动获取失败,请输入作者名称？");
      DEFAULT_ARGUMENTS.put("author", scanner.next());
    } else {
      DEFAULT_ARGUMENTS.put("author", gitConfig.get("name"));
    }
  }

  private static void getGitUserEmail() {
    if (Objects.nonNull(gitConfig)) {
      DEFAULT_ARGUMENTS.put("email", gitConfig.get("email"));
    }
  }

  private static void initGitConfig() {
    String config = readGitConfig();
    if (!config.isEmpty()) {
      // 对读入的数据进行格式化
      String[] replace = config
        .replace("\r\n\t", ";")
        .replaceFirst(";", "")
        .replaceAll("\\s+", "")
        .split(";");
      // 将git配置写入gitConfig中
      gitConfig = Arrays.stream(replace).distinct()
        .map(sa -> sa.split("="))
        .collect(Collectors.toMap(sa -> sa[0], sa -> sa[1]));
    }
  }

  private static String readGitConfig(){
    String userHomeCatalogue = null;
    String OSName = System.getProperty("os.name");
    // 根据不同的操作系统获取git的全局配置文件
    if (OSName.startsWith(WINDOWS)) {
      userHomeCatalogue = System.getProperty("user.home");
    } else if (OSName.startsWith(MAC)) {
      userHomeCatalogue = "~";
    }
    // 将git配置文件读入系统
    File file = new File(userHomeCatalogue + File.separator +  gitFileName);
    StringBuilder result = new StringBuilder();
    if (file.exists()) {
      try{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while((s = br.readLine()) != null){
          if (s.indexOf("=") > 0) {
            result.append(System.lineSeparator() + s);
          }
        }
        br.close();
      }catch(Exception e){
        System.out.println("文件读取失败");
        e.printStackTrace();
      }
    }
    return result.toString();
  }
}
