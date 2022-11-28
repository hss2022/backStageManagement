<template>
  <div class="manage">
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <!-- 如果需要像删除操作前的提示框 -->
      <!-- 用户的表单信息 -->
      <el-form
        ref="form"
        :inline="true"
        :model="form"
        label-width="80px"
      >
        <!-- 定义相关的表单域 -->

        <el-form-item label="菜名" prop="menuName">
          <el-input placeholder="请输入菜名" v-model="form.menuName"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="menuPrice">
          <el-input
            placeholder="请输入价格"
            v-model="form.menuPrice"
          ></el-input>
        </el-form-item>
        <el-form-item label="已出售" prop="todaySale">
          <el-input
            placeholder="请输入数量"
            v-model="form.todaySale"
          ></el-input>
        </el-form-item>
        <el-form-item label="是否剩余" prop="isResidue">
          <el-input
            placeholder="请输入true或false"
            v-model="form.isResidue"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
    <div class="manage-header">
      <el-button @click="dialogVisible = true" type="primary">
        +新增
      </el-button>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="menuName" label="菜名"> </el-table-column>
        <el-table-column prop="menuPrice" label="价格"> </el-table-column>
        <el-table-column prop="todaySale" label="今日出售"></el-table-column>
        <el-table-column
          prop="isResidue"
          :formatter="judgeResidue"
          label="是否剩余"
        ></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
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
      // total:10,
      form: {
        menuName: "",
        menuPrice: "",
        todaySale: "",
        isResidue: "",
        // residue:"`1 : 0 ? {{isResidue}}`"
      },
  
      //用户列表的数据
      tableData: [
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
    };
  },
  computed: {},
  methods: {
    judgeResidue(row, column, isResidue) {
      if (isResidue == true) {
        return "还有剩余";
      } else {
        return "已卖光";
      }
    },
    handleEdit(index,row){
      this.dialogVisible = true
      this.form.menuName=row.menuName
      this.form.menuPrice=row.menuPrice
      this.form.todaySale=row.todaySale
      this.form.isResidue=row.isResidue
    },
    handleDelete(index,row){
      this.$axios.post("/menu/delete",{
        menuName:row.menuName,
        menuPrice:row.menuPrice,
        todaySale:row.todaySale,
        isResidue:row.isResidue,
      }).then(res=>{
        if(res.checkout == true)
        alert("delete sucess")
        this.$router.go(0)
      })
    },
       //分页功能
    handleCurrentChange(currentPage) {
      //将当前页码赋值给pageNum
      this.pageNum = currentPage;
      // 得到当前页码 请求下一页数据
      this.$axios
        .post("/menu/page", {
          pageNum: this.pageNum, 
          pageSize: 9,
        })
        .then((res) => {
          console.log("res", res.data);
          this.tableData = res.data.records;
          console.log("pages", res.data.pages);
          console.log("tabledata", this.tableData);
        });
    },
  
    // 提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          //后续对表单数据的处理  注意函数调用加括号
          this.$axios
            .post("/menu/create", {
              menuName: this.form.menuName,
              menuPrice: this.form.menuPrice,
              todaySale: this.form.todaySale,
              isResidue: this.form.isResidue,
            })
            .then((response) => {
              if (response.data.checkout === true) {
                // this.$router.push({ path: "/main" });
                alert("success");
                this.$refs.form.resetFields();
                //关闭弹窗  并且关闭时清空数据
                this.dialogVisible = false;
                 this.$router.go(0)
              }
            });
        }
      });
    },
  
    getPage() {
      console.log(this.currentPage);
    },
    // 弹窗关闭时触发  注意自带的重置表单的方法
    handleClose() {
      this.$refs.form.resetFields();
    },
    // 取消按钮触发清空重置  然后再关闭弹窗
    cancel() {
      this.handleClose();
      this.dialogVisible = false;
    },
  },
  // watch:{
  //   tableData:{
  //     deep:true
  //   }
  // },
  mounted() {
    this.$axios.get("/menu/all").then((response) => {
      this.tableData = response.data;
    });
  },
  activated() {},
};
</script>

<style>
</style>