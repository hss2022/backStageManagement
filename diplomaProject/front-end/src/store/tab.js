export default{
    state:{
        isCollapse:false//用于展示菜单的展开和收起
    },
    mutations:{
        //修改菜单展开收起的方法
        collapseMenu(state){
            state.isCollapse = !state.isCollapse
        }
    }
}