<template>

  <div>
        <v-card
        class="mx-auto"
        max-width="1200"
        outlined
        raised
        shaped
    >
  
            <v-container class = "back">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ระบบชำระเงิน</h1>
      </v-flex>
    </v-layout>
    <v-form v-model="valid" ref="form">
    <v-row justify="center">
      <v-col cols="6">
        <v-select
          label="เลขกำกับใบสั่งยา"
          outlined
          v-model="Payment.doctorOrder"
          :items="doctororders"
          item-text="date"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>



    <v-row justify="center">
      <v-col  cols="6">
        <div >
        <v-select
          label="เลือกการชำระ"
          outlined
          v-model="Payment.paymentOption"
          :items="paymentOptions"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          
        ></v-select>
        </div>
      </v-col>
    </v-row>

    

   

    <div v-if="Payment.paymentOption == 2">

        <v-row justify="center">
      <v-col cols="6">
        
        <v-select
          label="เลือกธนาคาร"
          outlined
          v-model="Payment.typeBank"
          :items="typeBanks"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          
        ></v-select>
      
      </v-col>
      
    </v-row>

    
    </div>


</v-form>
    <v-row justify="center">
      <v-col cols="6">
        <v-btn @click="savePayment" :class="{ red: !valid, green: valid }">submit</v-btn>
        <v-btn style="margin-left: 20px;" @click="clear">clear</v-btn>
      </v-col>
    </v-row>


            </v-container>
        </v-card>
    </div>
</template>

<script>
import http from "../http-common";


export default {
  
  name: "Payment",
  data() {
    return {
      Payment: {
        doctorOrder: "",
        paymentOption: "",
        typeBank: 0,

      },
      check: false,
      check2: false,
      val:"",
      components: {
      },
      picker: new Date().toISOString().substr(0, 10),
      valid: false,
      pclear: false,
      doctororders: [],
      paymentOptions: [],
      typeBanks: [],
      prop:[]
          
    };
    
  },
  methods: {


    // get1() {
    //   http
    //     .get("/payment/")
    //     .then(response => {
    //       this.payment = response.data;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    
   
   
    get3() {
      http
        .get("/doctororder")
        .then(response => {
          this.doctororders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Video ใส่ combobox
    get2() {
      http
        .get("/paymentOption")
        .then(response => {
          this.paymentOptions = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    get4() {
      http
        .get("/typeBank")
        .then(response => {
          this.typeBanks = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม submit
    savePayment() {
      http
        .post("/paymentPost", this.Payment)
        .then(response => {
          console.log(response.data.id);
         // this.prop = response.data
          if(response.data.id == null){
              alert("error")

          }else {
            if(this.Payment.typeBank == 0){
                document.location.href = 'http://localhost:8080/bills/'+response.data.id;
            }
            else
              document.location.href = 'http://localhost:8080/bill/'+response.data.id;

          }
            
        })
        .catch(e => {
          console.log(e);
        });

      console.log(this.Payment);

    },
    clear() {
      this.$refs.form.reset();
     // this.pclear = false;
    },
    refreshList() {
      this.get2();
      this.get3();
      this.get4();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.get2();
    this.get3();
    this.get4();
    
  },
  
};

</script>
<style>
.font {
  font-family: 'Prompt', sans-serif;
  font-size: 30px ;
  color: black;
}
 .back {
 background-image: linear-gradient(0deg, rgba(224, 214, 220, 0.3), rgba(243, 76, 174, 0.3)), url(C:\team16-master\client\src\assets\g1.png);
  background-size: cover;
  background-blend-mode: multiply;
}

</style>>