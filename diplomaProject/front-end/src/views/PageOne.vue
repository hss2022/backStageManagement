<template>
<div>
  <body>
    <div id="myHeader">
        <img src="https://www.logosc.cn/uploads/icon/2018/10/29/14ada8ea-8572-49b1-85a3-632ee11b70bc.png" class="logo">
        <!-- <p id="title">商家介绍</p> -->
    </div>
    <div id="introduction">
        <p class="name">特色煲仔</p>
        <p class="information">商家品类 : 快餐、瓦罐汤</p>
        <p class="information">地址 : 福建省福州市闽侯县上街镇福州大学生活三区紫荆园餐厅</p>
        <p class="information">联系电话 : 152XXXXXXXX</p>
        <p class="information">营业时间 : 周一至周日 09:25-13:20,15:30-19:20</p>
        
    </div>
    <div id="tip">
        <p class="name2">公告 : </p>
        <p>每天现做家常菜，能吃到家里的味道！</p>
        <p>本店为福大食堂店，可送至宿舍楼下，欢迎同学们下单品尝。有漏掉的商品请联系我们补发或者退款，谢谢同学们的支持。</p>
        

    </div>
    <div id="other">
        需要更多信息请联系我们
    </div>

</body>
</div>

</template>

<script>
export default {
  
};
</script>

<style scope="">
  #myHeader{
            background-color: #f1f1f1;
            height:80px;
            text-align: center;
            font-size: 30px;
            padding-top: 20px;
            padding: 20px;
            letter-spacing: 5px;
        }
        #introduction{
            border: 2px solid #eaf0f3;
            height:270px;
        }
        #other{
            border: 2px solid #eaf0f3;
            background-color: #f1f1f1;
            padding: 40px;
            text-align: center;
            color: brown;
            font-size: 100%;
        }
        #tip{
            height: 200px;
            border: 2px solid #eaf0f3;
            padding-left: 70px;
        }
        #title{
            color: rgb(0, 0, 0);
            font-size: 70%;
        }
        .information{
            color: rgb(0, 0, 0);
            padding-left: 70px;
        }
        .name{
            color: rgb(107, 55, 0);
            font-size: 200%;
            padding-left: 70px;
        }
        .name2{
            color: rgb(107, 55, 0);
            font-size: 200%;
        }
        .logo{
            width: 75px;
            height: 75px;
        }
</style>