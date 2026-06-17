import type { MenuProps } from 'ant-design-vue'

export interface MenuItemConfig {
  key: string
  label: string
  path: string
}

export const menuItems: MenuItemConfig[] = [
  { key: 'home', label: '首页', path: '/' },
  { key: 'about', label: '关于', path: '/about' },
]

export const getMenuItems = (): MenuProps['items'] =>
  menuItems.map((item) => ({
    key: item.key,
    label: item.label,
  }))
