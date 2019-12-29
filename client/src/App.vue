<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span class="font">ระบบจัดการผู้ป่วยใน</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
       <v-toolbar-items>
        <v-btn class="font" @click="goHome" text>Home</v-btn>
        <v-btn class="font" @click="logout"  color="red" text>ออกจากระบบ</v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <v-content>
      <!-- <VideoRental/> -->
      <router-view />
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    username: 'Tanapon Kongjaroensk',
  }),

  methods: {
     logout(){
       this.$router.push("/");
       window.location.reload();
     },
     goHome(){
       if(this.$route.params.id == null){
          alert("please login")
       }else{
        this.$router.push("/home/"+this.$route.params.id);
        window.location.reload();
       }
     },

  }
};

</script>
<style>
.font {
  font-family: 'Prompt', sans-serif;
  font-size: 30px ;
}

</style>
