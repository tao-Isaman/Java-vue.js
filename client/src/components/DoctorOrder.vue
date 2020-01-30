<template>
  <div class="background">
  <v-container>
    <v-card
      class="mx-auto backcard"
      max-width="1400"
      height="100%"
      
    >
    <v-layout text-center wrap>
       <v-flex md12  class="mt-1">
                    <v-img
                    :src="require('../assets/pills.png')"
                    class="my-3"
                    contain
                    height="200"
                    ></v-img>
        </v-flex>
      <v-flex mb-4>
        <br />
        <h1 class="font">ระบบบันทึกคำสั่งจ่ายยา</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col>
        <v-form v-model="valid" ref="form">
          <div>  
              <v-flex md12>
                    <v-row justify="center">
                      <v-col cols="4"></v-col>
                        <v-col cols="7" align="center">


                          

                          <v-select class="font1"
                            v-model="doctorsOrder.examinationId"
                            :items="examinaton"
                            item-text="symptom"
                            item-value="id"
                            label="Examination"
                          ></v-select>

                            
                            <v-select class="font1"
                                v-model="doctorsOrder.medicineIds"
                                :items="medicine"
                                item-text="mname"
                                item-value="id"
                                label="Medicine (ชื่อยา)"
                                multiple
                                chips
                            ></v-select>


                            <v-select class="font1"
                                v-model="doctorsOrder.medicationTypeIds"
                                :items="medicationType"
                                item-text="typeName"
                                item-value="id"
                                chips
                                multiple
                                label="Medication Type (ประเภทการรับประทาน)"
                            ></v-select>

                            <v-text-field class="font1"
                              label="Drug Allergies (แพ้ยา)"
                              v-model="doctorsOrder.allergies"
                              :rules="[(v) => !!v || 'Item is required']"
                            ></v-text-field>

                             <v-text-field class="font1"
                              label="Drug Allergies Reaction (อาการแพ้ยา)"
                              v-model="doctorsOrder.reaction"
                              :rules="[(v) => !!v || 'Item is required']"
                            ></v-text-field>

                            <v-text-field class="font1"
                              label="Prescription Number (เลขใบสั่งจ่ายยา)"
                              v-model="doctorsOrder.prescriptionNumber"
                              :rules="[(v) => !!v || 'Item is required']"
                            ></v-text-field>

                             <br><br>
                            
                            <v-btn @click="checkMedecine" :class="{ red: !valid, green: valid }" >Save</v-btn>
                            <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                            <v-btn @click="show" style="margin-left:100px;">Prescription</v-btn>

                        </v-col>
                        <v-col cols="1"></v-col>
                    </v-row>
                </v-flex>
              
            
          </div>
        </v-form>
      </v-col>
    </v-row>
    </v-card>
  </v-container>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "doctorsOrder",
  data() {
    return {
      doctorsOrder: {
        examinationId: "",
        medicineIds: [],
        medicationTypeIds: [],
        allergies: "",
        reaction: "",
        prescriptionNumber : "",
        
      },
      valid: false,
      menu2:true,
      examinaton:[],
      medicationType:[],
      medicine:[],
      date:"",
     
      
    
    };
  },
  methods: {
    /* eslint-disable no-console */

    getExamination() {
      http
        .get("/examinationSystem")
        .then(response => {
          this.examinaton = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    show(){
                this.$router.push("/prescriptionNumber");
            },
// this.examinationSystem.Patient_ID
    //  findPatient() {
    //   http
    //     .get("/examinationystem/"+this.doctorsOrder.Patient_ID)
    //     .then(response => {
    //       // console.log(response);
    //       this.examinaton = response.data;
    //       console.log(response.data);
    //       if (response.data != null) {
    //         this.patientName = response.data.name;
    //         this.patientCheck = response.status;
    //       } else {
    //         this.clear()
    //       }          
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    //   this.submitted = true;
    // },

    getMedicine() {
      http
        .get("/medicine")
        .then(response => {
          this.medicine = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล MedicationType ใส่ combobox
    getMedicationType() {
      http
        .get("/medicationType")
        .then(response => {
          this.medicationType = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    checkMedecine(){
      if((this.doctorsOrder.medicationTypeIds.length > this.doctorsOrder.medicineIds.length) || (this.doctorsOrder.medicationTypeIds.length < this.doctorsOrder.medicineIds.length)){
        console.log("เข้าฟังก์ชั่น")
                   const options2 = { title: "Alert", size: "sm" };

        this.$dialogs.alert("เลือกจำนวน Medication Type ให้สอดคล้องกับจำนวน Medicine",options2);
        
      }
      else {
        this.setDoctorsOrder();
      }
    },

   
  
    setDoctorsOrder() {
      console.log("this.doctorsOrder = " + this.doctorsOrder);
      http
        .post( "/postDoctor",
            this.doctorsOrder)
        .then(response => {               
          console.log(response);                                 
          const options2 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("Save Successfully",options2); 
          this.clear();
        })
        .catch(e => {
          console.log(e);
          const options2 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("Can Not Save!",options2);
        
        });
      //  \ไม่สามรถบันทึกคำสั่งจ่ายยาได้
      console.log(this.doctorsOrder);
      this.submitted = true;
    },
    
    clear() {
      this.$refs.form.reset();
    },


    refreshList() {
      this.getMedicine()
      this.getExamination();
      this.getMedicationType();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getMedicine()
    this.getExamination();
    this.getMedicationType();
    // this.postDoctor.dAllergies();
    // this.postDoctor.rAllergies();
  }
};
</script>


<style scoped>

.background{
  background-image: linear-gradient(0deg, rgba(255, 255, 255, 0.1),rgba(255, 255, 255, 0.1)),url(../assets/173921.jpg);
  background-size: cover;;
  background-position: bottom;
  background-repeat: no-repeat;
  height: 100%;
}
.font{
  font-family: 'Kanit', sans-serif;
  font-size: 50px ;
  color: rgb(0, 0, 0);
  opacity: 0.7;
}

.font1{
  font-family: 'Kanit', sans-serif;
  font-size: 20px ;
  color: black;
  opacity: 0.85;
}
.backcard{
  background-image:  linear-gradient(0deg, rgba(188, 226, 243, 0.144),rgba(188, 226, 243,  0.144)),url(../assets/287212-P6KAO6-535.jpg);
  background-size: cover;
  background-position: bottom;
  background-repeat: no-repeat;
  width: 100%;
  height: 100%;
  opacity: 0.85;
  padding: 0 0;
}
</style>
