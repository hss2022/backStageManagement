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
        :rules="rules"
        :inline="true"
        :model="form"
        label-width="80px"
      >
        <!-- 定义相关的表单域 -->
        <el-form-item label="姓名" prop="name">
          <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input placeholder="请输入电话" v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="addr">
          <el-input placeholder="请输入地址" v-model="form.addr"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input placeholder="请输入密码" v-model="form.password"></el-input>
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
        <el-table-column prop="clientName" label="姓名"> </el-table-column>
        <el-table-column prop="phone" label="电话"> </el-table-column>
        <el-table-column prop="address" label="地址">
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
      currentPage: 2,
      form: {
        name: "",
        phone: "",
        addr: "",
        password: "",
      },
      rules: {
        name: [{ required: true, message: "请输入姓名" }],
        phone: [{ required: true, message: "请输入电话" }],
        password: [{ required: true, message: "请输入密码" }],
        addr: [{ required: true, message: "请输入地址" }],
      },
      //用户列表的数据
      tableData: [
        {
          clientName: "hss",
          phone: "123",
          address: "fzu",
          password: "",
        },
        {
          clientame: "a",
          phone: "123",
          addr: "fzu",
          password: "",
        },
        {
          clientName: "b",
          phone: "123",
          address: "fzu",
          password: "",
        },
      ],
    };
  },
  methods: {
    // 提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          //后续对表单数据的处理  注意函数调用加括号
          this.$axios
            .post("/client/create", {
              phone: this.form.phone,
              clientName: this.form.name,
              address: this.form.addr,
              password: this.form.password,
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
    handleCurrentChange(currentPage) {
      //将当前页码赋值给pageNum
      this.pageNum = currentPage;
      // 得到当前页码 请求下一页数据
      this.$axios
        .post("http://127.0.0.1:9090/client/page", {
          pageNum: this.pageNum,
          pageSize: 10,
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
    this.$axios.get("/client/all").then((response) => {
      this.tableData = response.data;
    });
    // this.handleCurrentChange(this.currentPage)
  },
  activated() {},
};
</script>

<style>
</style>