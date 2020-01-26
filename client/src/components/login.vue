<template>
<div >
<v-card
    class="mx-auto"
    max-width="700"
    img = "@/assets/g1.png"
    outlined
    raised
    shaped
  >
  <v-container class = "back2">
      <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <v-row align="center" justify="center">
        <v-img src="@/assets/SUTH.png"
        max-width="500"
        max-height="300"
      ></v-img>
      </v-row>
      </v-flex>
    </v-layout>
                <v-form v-model="valid" ref="form">
                <v-row justify="center">
                <v-col cols="6">                
                  <v-text-field
                    outlined
                    label="Username"
                    v-model="User.username"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-text-field>
                </v-col>
                 </v-row>

                <v-row justify="center">
                <v-col cols="6">
                  <v-text-field
                    outlined
                    label="Password"
                    type = password
                    v-model="User.password"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-text-field>
                    </v-col>
                 </v-row>
                 </v-form>

                 <v-row justify="center">
                    <v-btn x-large @click="Login" :class="{ red: !valid, green: valid }">Login</v-btn>
                </v-row>
            

  </v-container>
  </v-card>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: "Login",
  data() {
    return {
      User: {
          username : "",
          password : ""       
      },
      valid: false,
      customerCheck: false
    };
  },
  methods: {
    /* eslint-disable no-console */
    Login() {
      http
        .post(
          "/User/login",
          this.User     
        )
        .then(response => {
          console.log(response);
          if(this.User.username == response.data){
              {
                    const options1 = { title: "ประกาศ!", size: "sm" };
                        this.$dialogs.alert("เข้าสู่ระบบ", options1);
                  }
              this.$router.push("/home/"+this.User.username);
          }
          else{
              {
                    const options1 = { title: "ประกาศ!", size: "sm" };
                        this.$dialogs.alert("username or password invalid !", options1);
                  }           
          }
        })
        .catch(e => {
          console.log(e);
          alert("Error Username or Password invalid !")
        });
        console.log(this.User);
    },
 
    
    /* eslint-enable no-console */
  },
  mounted() {
   
  }
};
</script>
<style>
    .back2 {
  background-image: url(../assets/g1.png);
  background-size: cover;
}

</style>