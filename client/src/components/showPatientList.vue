<template>
<div>
     <v-container style="height: 500%; margin-right:15%">
          <v-card style="width:1000%; margin:auto; background-color:#FFFFFF">
            <v-layout text-center wrap>
              <v-flex mb-4>
                <br />
                <h1 class="display-1 font-weight-bold mb-3">รายชื่อผู้ป่วยใน</h1>
              </v-flex>
            </v-layout>
            <v-row justify="center">
              <v-col cols="100">
                <v-container></v-container>
                <v-container></v-container>

                <v-data-table :headers="headers" :items="PatientList" :items-per-page="5"></v-data-table>

              </v-col>
            </v-row>
          </v-card>
        </v-container>
    

</div>

 
      
</template>

<script>
import http from "../http-common";


export default {
  
  name: "Payment",
  data() {
    return {
      PatientList:'',
      headers: [
        { text: "เลขบัตรประจำตัวประชาชน", value: "nationalID" },
        { text: "ชื่อผู้ป่วย", value: "name" },
        { text: "น้ำหนัก kg.", value: "weigth"},
        { text: "ส่วนสูง cm.", value: "hight" },
        { text: "สิทธิการรักษา", value: "benefit.name" },
        { text:  "เพศ", value: "gender.name" },
        { text: "จังหวัด", value: "province.name" },
      ]
          
    };
    
  },
  methods: {
      /* eslint-disable no-console */

      getList(){
        http
                .get("/patient")
                .then(response => {
                  this.PatientList = response.data;
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
      },

    /* eslint-disable no-console */
  },
  mounted() {
      this.getList()
    

  },
  
};

</script>
<style>
.font {
  font-family: 'Prompt', sans-serif;
  font-size: 30px ;
  color: black;
}
 

</style>>