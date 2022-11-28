<!-- eslint-disable max-len -->
<template>
  <body id="poster">
    <el-form class="login-container" label-position="" label-width="0px">
      <h3 class="login-title">
        系统登陆
        <!-- <el-button @click="toRegister()">点我注册</el-button> -->
      </h3>
       <el-alert v-show="isTip" title="账号或密码错误" type="error" center show-icon>
        </el-alert>
      <el-form-item label="">
        <el-input
          type="text"
          v-model="form.loginName"
          placeholder="账号"
        ></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input
          type="password"
          v-model="form.password"
          placeholder="密码"
        ></el-input>
      </el-form-item>

      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%; background: #505458; border: none"
          @click="Login()"
        >
          立即登录
        </el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      isTip:false,
      form: {
        loginName: "",
        password: "",
      },
    };
  },
  methods: {
    //  Login() {
    //    this.$axios({
    //     method:"post",
    //     url:'127.0.0.1:8080/login',
    //     data:this.form,

    //   }).then(response=>{
    //     if(response.data.checkout === true){
    //       this.$router.push({path: '/about' })
    //     }

    //   })
    // }
    Login() {
      this.$axios
        .post("/login", {
          phone: this.form.loginName,
          password: this.form.password,
        })
        .then((response) => {
          // console.log(response);
          // console.log(response.data.phone);
          // console.log(this.form.password);
          if (response.data.phone != null) {
            console.log(response);
            this.$store.state.clientName = response.data.clientName
            this.$store.state.phone = response.data.phone
            this.$store.state.address = response.data.address
                  // console.log(1,this.$store)
                  //       console.log(2,this.$store.state)
            this.$router.push({ path: "/main" });
          } else {
            this.isTip = true;
          }
        });
    },
  },
};
// ToRegister() {
//   this.$router.push({ path: '/register' });
// },
// Login() {
//   this.axios({
//     method: 'POST',
//     url:'http://127.0.0.1:8080/login',
//     params:{
//       account:this.form.loginName,
//       password:this.form.password
//     }
//   }).then(response=>{
//     if(response.data.checkout === true){
//       this.$router.push({path: '/about'})
//     }else{
//       alert("账号或密码错误")
//     }
//   })
// },
</script>

<style  scoped>
#poster {
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: 0px;
  padding: 0px;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login-tilte {
  margin: 0px auto 40px auto;
  text-align: center;
  color: 505458;
}
</style>