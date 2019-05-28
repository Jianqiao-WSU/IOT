/* eslint-disable */
const base = 'Main'

export default [{
  name: 'Video',
  meta: {
    title: '摄像头数据',
    icon: 'video'
  },
  sub: [{
    name: 'Live',
    meta: {
      index: `/Video/Live`,
      title: '摄像头直播',
      type: 'menu',
      active: false
    }
  },{
    name: 'VideoList',
    meta: {
      index: `/Video/VideoList`,
      title: '视频列表',
      type: 'menu',
      active: false
    }
  }]
}, {
  name: 'WiFi',
  meta: {
    title: 'Wi-Fi数据',
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
      title: '更改密码',
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
