<template>
  <el-row>
    <el-col :span="8">
      <el-card class="box-card">
        <div class="user">
          <img src="../assets/images/logo.png" alt="" />
          <div class="user-info">
            <p class="name">Admin</p>
            <p class="access">超级管理员</p>
          </div>
        </div>
        <div class="login-info">
          <p>上次登录时间：<span>2022.11.15</span></p>
          <p>上次登录的地点：<span>福州</span></p>
        </div>
      </el-card>
      <el-card style="margin-top: 5px; height: 45vh">
        <el-table :data="tableData" style="width: 100%">
          <!-- <el-table-column 
          prop="name" 
          label="品牌" 
          >
          </el-table-column>
          <el-table-column 
          prop="todayBuy" 
          label="今日购买" 
          >
          </el-table-column>
            <el-table-column 
          prop="monthBuy" 
          label="本月购买" 
          width="180">
          </el-table-column>
            <el-table-column 
          prop="totalBuy" 
          label="总购买" 
          >
          </el-table-column> -->
          <el-table-column
            v-for="(val, key) in tableLabel"
            :key="key"
            :prop="key"
            :label="val"
          />
        </el-table>
      </el-card>
    </el-col>
    <el-col :span="16" style="padding-left: 10px">
      <div class="num">
        <el-card
          v-for="item in countData"
          :key="item.name"
          :body-style="{ display: 'flex', padding: 0 }"
        >
          <i
            class="icon"
            :class="`el-icon-${item.icon}`"
            :style="{ background: item.color }"
          ></i>
          <div class="detail">
            <p class="price">{{ item.value }}</p>
            <p class="desc">{{ item.name }}</p>
          </div>
        </el-card>
      </div>
      <el-card style="height: 390px">
        <!-- 柱状图 -->
        <div ref="echarts1" style="height: 350px"></div>
      </el-card>
      <!-- <div class="graph">
        <el-card style="height:260px"></el-card>
        <el-card style="height:260px"></el-card>
      </div> -->
    </el-col>
  </el-row>
</template>

<script>
import * as echarts from "echarts";
export default {
  // components:echarts,
  data() {
    return {
      tableData: [
        {
          menuName: "麻婆豆腐",
          menuPrice: 20,
          todaySale: 60,
        },
        {
          menuName: "玉米排骨汤",
          menuPrice: 20,
          todaySale: 60,
        },
        {
          menuName: "白斩鸡",
          menuPrice: 20,
          todaySale: 60,
        },
        {
          menuName: "红烧狮子头",
          menuPrice: 20,
          todaySale: 60,
        },
        //  {
        //   name:'乡村炖品',
        //   todayBuy:70,
        //   monthBuy:1600,
        //   todaySale:750,
        //   monthSale:2600
        // },
        //    {
        //   name:'豪客吉',
        //   todayBuy:70,
        //   monthBuy:1600,
        //   todaySale:750,
        //   monthSale:2600
        // },
        //    {
        //   name:'麦当劳',
        //   todayBuy:70,
        //   monthBuy:1600,
        //   todaySale:750,
        //   monthSale:2600
        // },
      ],
      //图表数据
      echartsData: [
        {
          menuName: "鱼香肉丝",
          menuPrice: "15",
          todaySale: 20,
          isResidue: true,
        },
        {
          menuName: "鱼香肉丝",
          menuPrice: "15",
          todaySale: 20,
          isResidue: true,
        },
        {
          todaySale: 20,
          menuName: "鱼香肉丝",
          menuPrice: "15",
          isResidue: true,
        },
      ],
      tableLabel: {
        menuName: "招牌菜",
        menuPrice: "价格",
        todaySale: "今日卖出",
        // totalSale:'今日销售额',
        // monthSale:'本月销售额'
      },
      countData: [
        {
          name: "完成订单",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "未完成订单",
          value: 1234,
          icon: "s-goods",
          color: "#2ec7c9",
        },
        {
          name: "总订单数",
          value: 2100,
          icon: "mouse",
          color: "#ffb980",
        },
        {
          name: "完成订单金额",
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          name: "未完单金额",
          value: 1234,
          icon: "s-goods",
          color: "#2ec7c9",
        },
        {
          name: "总销售额",
          value: 210,
          icon: "mouse",
          color: "#ffb980",
        },
      ],
    };
  },
  methods: {
    getCards() {
      this.$axios.get("/order/cards").then((res) => {
        this.countData[0].value = res.data.finished;
        this.countData[1].value = res.data.unfinished;
        this.countData[2].value = res.data.sum;
        this.countData[3].value = res.data.finishedSum;
        this.countData[4].value = res.data.unfinishedSum;
        this.countData[5].value = res.data.saleSum;
      });
    },
    getSign() {
      this.$axios.get("/menu/sign").then((res) => {
        // console.log(1,res);

        this.tableData = res.data;
      });
    },
    getBar() {
      this.$axios.get("/menu/every").then((res) => {
        console.log("echartsRes--", res.data);
        const echarts1 = echarts.init(this.$refs.echarts1);
        var option = {};
        option = {
          xAxis: {
            // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
            // data: ["Mon", "Tue", "Wed"],
            data: res.data.map((item) => item.menuName),
          },
          yAxis: {},
          series: [
            {
              type: "bar",
              // data: [23, 24, 18, 25, 27, 28, 25],
              // data: [23, 24, 18],
              data: res.data.map((item) => item.todaySale),
            },
          ],
        };
        echarts1.setOption(option);
        // console.log("echartsData",this.echartsData)
      });
      //  this.getEcharts();
    },
    getEcharts() {
      console.log("2");
    },
  },
  mounted() {
    this.getCards();
    this.getSign();
    this.getBar();
  },
};
</script>

<style lang="less" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }
  .user-info {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: #999999;
    }
  }
}
.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;
    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
.num {
  display: flex;
  flex-wrap: wrap;
  .icon {
    width: 80px;
    height: 80px;
    font-size: 30px;
    line-height: 80px;
    text-align: center;
    color: #fff;
  }
  .detail {
    margin-left: 15px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .price {
      font-size: 30px;
      margin-bottom: 10px;
      line-height: 30px;
      height: 30px;
    }
    .desc {
      font-size: 14px;
      color: #999;
      text-align: center;
    }
  }
  .el-card {
    width: 32%;
    margin-bottom: 10px;
  }
}
.graph {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  .el-card {
    width: 48%;
  }
}
</style>