<template>
    <div class="back">
        <v-card
        class="mx-auto"
        max-width="1200"
        outlined
        raised
        shaped
    >



   <div id="printMe">
        <v-row justify="center">
      <v-col cols="1">
       <h1> ใบเสร็จ </h1>
      </v-col>

    </v-row>
    <v-row justify="center">
      <v-col cols="6">
       <h2> ชื่อผู้ป่วย : 
          {{pname}} 
       </h2>
      </v-col>
       </v-row>

       <v-row justify="center">
      <v-col cols="6">
       <h2> เลขกำกับใบสั่งยา :   
         {{payment.doctorOrder.id}}
       </h2>
      </v-col>
       </v-row>

       <v-row justify="center">
      <v-col cols="6">
       <h2> วันที่จ่ายยา :    
          {{payment.doctorOrder.date}}
       </h2>
      </v-col>
       </v-row>

       <v-row justify="center">
      <v-col cols="6">
       <h2> การชำระเงิน :   
         {{payment.paymentOption.name}}  ( {{payment.typeBank.name}} )
       </h2>
      </v-col>
       </v-row>

      <v-row justify="center">
      <v-col cols="6">
       <h2> รายการยา :   
       </h2>
       <v-row >
       <div>
           <ul style="margin-left: 100px;">
             <h2> 
          <li v-for="(item) in medicine" :key="item.id">
              {{item[1]}}  ราคา   {{item[2]}} บาท
          </li>
             </h2>
          </ul> 
         <h2> นอนห้อง {{bedname}} ทั้งหมด {{Day}} วัน วันละ {{bedprice}} บาท </h2>
      </div>
      </v-row>
      </v-col>
       </v-row>
              <v-row justify="center">
      <v-col cols="6">
            <h2> ราคายารวมทั้งสิ้น :   {{medicinetotal}} บาท
       </h2>
       <h2> ราคาเตียงรวมทั้งสิ้น :   {{bedPricetotal}} บาท
       </h2>
       <h2> รวมค่าใช้จ่ายทั้งหมด :   {{bedPricetotal + medicinetotal}} บาท
       </h2>
      </v-col>
        </v-row>

                      <v-row justify="center">
      <v-col cols="6">
            <h2> หมายเหตุ :   {{payment.note}} บาท </h2>
      </v-col>
        </v-row>

   </div>
                                         <v-row justify="end">
                                    <v-col cols="end">                     
                                          <v-btn class="ma-2" tile  color="green" @click="print">
                                                  <v-icon left></v-icon> พิมพ์ใบเสร็จที่นี่
                                          </v-btn>
                                    </v-col>
                                 </v-row>
        </v-card>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      payment: {
        id:"",
        note:"",
        doctorOrder:{
          id:"",
          date:"",
          // medicine:{
          //   id:null,
          //   name:"",
          //   price:"",
          // }
        },
        paymentOption:{
          id:"",
          name:"",
        },
        typeBank:{
          id:"",
          name:"",
        }
      },
      medicine:[],
      medicineID:[],
      medicineName:[],
      medicinePrice:[],
      medicinetotal:0,
      NationalID:"",
      Day:"",
      bedPricetotal:0,
      bedprice:0,
      bedname:"",
      pname:""
      };
    },
  methods: {
    /* eslint-disable */

    
    print() {
      this.$htmlToPaper('printMe');
    },

    getDate(id){
        http
            .get("/getDate/"+id)
            .then(response => {
              console.log(response.data);
              this.Day = response.data;
              this.bedPricetotal = this.Day*this.bedprice;
              console.log(this.bedprice);
              console.log(this.bedPricetotal);
              
              
            })
            .catch(e => {
              console.log(e);
            });
    },
    getDay(){
        http
            .get("/reservationByPatient/"+this.NationalID)
            .then(response => {
              console.log(this.NationalID);
              console.log("*****");
              console.log(response.data[0]);
              console.log("******");
              this.bedprice = response.data[0][1];
              this.getDate(response.data[0][5]);
              this.bedname = response.data[0][2];
              this.pname = response.data[0][6];
              
            })
            .catch(e => {
              console.log(e);
            });
    },
    get1() {
          http
            .get("/payment/"+this.$route.params.id)
            .then(response => {
              console.log(response);
              this.payment = response.data;
              if(response.data.typeBank.id == null)
                this.payment.typeBank.id = 0;
              console.log(this.payment);
              
            })
            .catch(e => {
              console.log(e);
            });
        },
          get2() {
          http
            .get("/doctororder/"+this.$route.params.id)
            .then(response => {
              this.doctororder = response.data;
              console.log(this.doctororder);
              this.NationalID = response.data.ex.patient.nationalID;
              console.log(this.NationalID);
              this.getDay();
            })
            .catch(e => {
              console.log(e);
            });
        },
        getMed() {
          http
            .get("/medicineItem/"+this.$route.params.id)
            .then(response => {
              this.medicine = response.data;
              console.log(this.medicine);
              var i,j;
            for (i = 0; i < response.data.length; i++) {
                this.medicineID[i] = response.data[i][0];
                this.medicineName[i] = response.data[i][1];
                this.medicinePrice[i] = response.data[i][2]; 
                this.medicinetotal = this.medicinePrice[i] + this.medicinetotal;     
            }
            console.log(this.medicineName);
            console.log(this.medicinePrice);    
            console.log(this.medicinetotal);        
            })
            .catch(e => {
              console.log(e);
            });
            
         
        },
        
  
  clear() {
      this.$refs.form.reset();
      this.pclear = false;
    },
    refreshList() {
      this.get1();
      this.getMed();
      this.get2();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.get1();
    this.getMed();
    this.get2();
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
</style>