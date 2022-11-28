<template>
 
  <el-menu
    default-active="1-4-1"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    :collapse="isCollapse"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <h3>{{isCollapse ? '后台' :'食堂点餐后台管理系统'}}</h3>
    <!-- index是唯一标识  如果没有这个当我们点击时系统不知道点击的是哪一个 -->
    <el-menu-item
      @click="clickMenu(item)"
      v-for="item in noChildren"
      :key="item.name"
      :index="item.name"
    >
      <i :class="`el-icon-${item.icon}`"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>

    <el-submenu
      v-for="item in hasChildren"
      :key="item.label"
      :index="item.label"
    >
      <template slot="title">
        <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title">{{ item.label }}</span>
      </template>

      <el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
        <el-menu-item
        @click="clickMenu(subItem)" 
        :index="subItem.path">{{ subItem.label }}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "CommonAside",
  data() {
    return {
      // isCollapse: false,   交给vuex来管理后不能重复定义
      menuData: [
        {
          path: "/main",
          name: "main",
          label: "首页",
          icon: "s-home",
          url: "Home/Home",
        },
        // {
        //   path: "/main/Mall",
        //   name: "Mall",
        //   label: "店家管理",
        //   icon: "video-play",
        //   url: "MallManage/MallManage",
        // }, 
          {
          // path: "/main/Mall",
          // name: "Mall",
          label: "特色煲仔",
          icon: "s-shop",
          children:[
            {
              path: "/main/mall1",
              name: "MallOne",
              label: "菜单管理",
              icon: "s-shop",
              url: "Other/PageOne",
            },
            {
              path: "/main/mall2",
              name: "MallTwo",
              label: "订单管理",
              icon: "s-shop",
              url: "Other/PageTwo",
            }
            ]
        },
        {
          path: "/main/user",
          name: "user",
          label: "用户管理",
          icon: "user",
          url: "UserManage/UserManage",
        },
        // {
        //     path:'/index',
        //     name:'index',
        //     label:'首页',
        //     icon:'s-home',
        //     url:'Home/Home'
        // },
        {
          label: "其他",
          icon: "location",
          children: [
            {
              path: "/main/page1/",
              name: "page1",
              label: "关于店家",
              icon: "setting",
              url: "Other/PageOne",
            },
            {
              path: "/main/page2",
              name: "page2",
              label: "开发者",
              icon: "setting",
              url: "Other/PageTwo",
            },
          ],
        },
      ],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    // 点击菜单  当页面路由与当前路由不一致时才跳转（觉得不是很有必要）
    clickMenu(item){
        if(this.$route.path != item.path && !(this.$route.path === '/main/home' && (item.path === '/main')))
       { this.$router.push(item.path)}
    }
  },
  computed: {
    //没有子菜单
    noChildren() {
      return this.menuData.filter((item) => !item.children);
    },
    // 有子菜单
    hasChildren() {
      return this.menuData.filter((item) => item.children);
    },
    isCollapse(){
      return this.$store.state.tab.isCollapse
    }
  },
};
</script>

<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu{
      height: 100vh; 
}
h3{
    color:#fff;
}
</style>