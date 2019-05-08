/* eslint-disable */
const base = 'Main'

export default [{
  name: 'WiFi',
  meta: {
    title: 'WiFi数据',
    icon: 'wifi'
  },
  sub: [{
    name: 'FrameInfo',
    meta: {
      index: `/WiFi/FrameInfo`,
      title: 'Frame数据',
      type: 'menu',
      active: false
    }
  },{
    name: 'BoundaryInfo',
    meta: {
      index: `/WiFi/BoundaryInfo`,
      title: '室内地图边界数据',
      type: 'menu',
      active: false
    }
  },{
    name: 'WallsInfo',
    meta: {
      index: `/WiFi/WallsInfo`,
      title: '室内墙壁数据',
      type: 'menu',
      active: false
    }
  }]
}, {
  name: 'Settings',
  meta: {
    title: '设置',
    icon: 'tools'
  },
  sub: [{
    name: 'Account',
    meta: {
      index: `/Settings/Account`,
      title: '账号',
      type: 'menu',
      active: false
    }
  },
  {
    name: 'DeleteAcc',
    meta: {
      index: `/Settings/DeleteAcc`,
      title: '删除用户',
      type: 'menu',
      active: false
    }
  }]
}]
