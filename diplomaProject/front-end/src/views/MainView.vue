<template>
  <div class="about">
   <el-container>
  <el-aside width="auto">
    <CommonAside/>
  </el-aside>
  <el-container>
    <el-header>
    <CommonHeader/>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
  </div>
</template>
<script>
import CommonAside from '../components/CommonAside'
import CommonHeader from '../components/CommonHeader'
export default {
  components:{CommonAside,CommonHeader}
}
</script>
<style lang="less">
.el-header{
  padding: 0;
}
.el-menu{
  border-right: none;
}
</style>