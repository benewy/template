import path from 'path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '~/components/': `${path.resolve(__dirname, 'node_modules/@beneway/ts-seven-lists-components/src')}/`,
    },
  },
  plugins: [vue()]
})
