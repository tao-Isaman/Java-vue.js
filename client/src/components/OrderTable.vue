<template>
  <v-container md12>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Precription</h1>
      </v-flex>
      
    </v-layout>

    <v-row justify="center">
      <v-col cols="10">
        <table style="width:100%">

          <tr>
            <td>Doctor'S Order No.</td>
            <td>Date</td>
            <td>Patient Name</td>
            <td> Examination </td>
            <td>Medicine</td>
            <td>Medication Type</td>
            <td>Doctor</td>
          </tr> 
          <tr v-for="(item) in items" :key="item.id">        
                <td>{{item[0]}}</td>  
                <td>{{item[1]}}</td>
                <td>{{item[2]}}</td> 
                <td>{{item[3]}}</td>
                <td>{{item[4]}}</td>
                <td>{{item[5]}}</td>
                <td>{{item[6]}}</td>
          </tr>
        </table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "OrderData",
  data() {

    return {

        doctorOrder:{
            id:"",
            
          },
    
     
      items: [],
      data:[],
      allItem:[],
      
    };
  },
  
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล DoctorOrder ใส่ combobox
    getOrderTable() {
      http
        .get("/ordertable/"+this.$route.params.id)
        .then(response => {
          this.items = response.data ;
          console.log(this.allItem);
        })
        .catch(e => {
          console.log(e);
        });
    },    
    
    refreshList() {
      this.items();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getOrderTable();
  }
};
</script>
