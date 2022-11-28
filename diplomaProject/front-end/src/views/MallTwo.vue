<template>
  <div class="manage">
    <div class="manage-header">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="orderId" label="订单ID"> </el-table-column>
        <el-table-column label="客户菜单">
          <!-- <template slot-scope="scope"> -->
          <!-- <el-popover
              trigger="hover"
              placement="right"
              :dishData="dishData"
            >
               <p>姓名: {{ scope.row.name }}</p> -->
          <!-- <p v-for="dish in dishData" :key="dish.menuName">
                {{ dish.menuName }}:{{ dish.dishQuantity }}
              </p> -->
          <!-- <p>住址: {{ scope.row.address }}</p> -->
          <!-- <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div> -->
          <!-- <el-button>hello</el-button>
            </el-popover> -->
          <!-- </template> -->
          <template slot-scope="scope">
            <el-popover
              placement="right"
            
              :width="100"
              trigger="manual"
              v-model="scope.row.visible"
              :dishData="dishData"
            >
              <p v-for="dish in dishData" :key="dish.menuName">
                {{ dish.menuName }}:{{ dish.dishQuantity }}
              </p>
              <template #reference>
                <el-button @click="getDishes(scope.row)">获取详情</el-button>
              </template>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="orderTime" label="下单时间"> </el-table-column>
        <el-table-column prop="payment" label="订单金额"> </el-table-column>
        <el-table-column prop="phone" label="客户电话"> </el-table-column>
        <el-table-column prop="address" label="客户地址"></el-table-column>
        <el-table-column prop="clientName" label="客户姓名"></el-table-column>
        <!-- <el-table-column prop="todaySale" label="客户姓名"></el-table-column> -->
        <el-table-column
          prop="finished"
          :formatter="judgeFinished"
          label="是否完成订单"
        ></el-table-column>
        <el-table-column label="更改状态">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >更改</el-button
            >
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        :total="100"
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
    
      dishData: [
        {
          orderId: "",
          menuName: "米饭",
          dishQuantity: 2,
        },
        {
          orderId: "",
          menuName: "爆炒青菜",
          dishQuantity: 2,
        },
      ],
      //订单列表的数据
      tableData: [
        {
          orderId: "1",
          orderTime: "2022-11-26 20:43:20",
          payment: "20",
          phone: "123456678910",
          address: "32",
          clientName: "hss",
          finished: false,
          visible: false,
        },
        {
          orderId: "2",
          phone: "123456678910",
          orderTime: "2022-11-26 20:43:20",
          address: "32",
          clientName: "hss",
          finished: false,
        },
      ],
    };
  },
  computed: {},
  methods: {
    //获取订单的菜品数目
    getDishes(row) {
      row.visible = !row.visible;
      this.axios.post("/order/dishes",{
        orderId:row.orderId
      }).then(
        (res)=>{
          this.dishData = res.data
          console.log("res",res)
          console.log(this.dishData)
        }
      )
      // console.log(thisis);
      // console.log(1);
      // this.$router.go(0)
    },

    judgeFinished(row, column, finished) {
      if (finished == true) {
        return "已完成";
      } else {
        return "未完成";
      }
    },

    handleEdit(index, row) {
      this.$axios
        .post("/order/switch", {
          orderId: row.orderId,
        })
        .then((res) => {
          if (res.checkout == true) this.$router.go(0);
        });
      this.$router.go(0);
    },

    //分页功能
    handleCurrentChange(currentPage) {
      //将当前页码赋值给pageNum
      this.pageNum = currentPage;
      // 得到当前页码 请求下一页数据
      this.$axios
        .post("/order/page", {
          pageNum: this.pageNum,
          pageSize: 7,
        })
        .then((res) => {
          // console.log("res", res.data);
          this.tableData = res.data.records;
          // console.log("pages", res.data.pages);
          // console.log("tabledata", this.tableData);
        });
    },
  },
  mounted() {
    // this.$axios.get("/order/all").then((response) => {
    //   this.tableData = response.records;
    //   console.log("response"+response)
    // });
    // console.log("tableData"+this.tableData)
    this.handleCurrentChange(1);
  },
  activated() {},
};
</script>

<style>
</style>