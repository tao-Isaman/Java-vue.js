<template>
    <div class="background">
    <v-container>
        <v-card
        class="mx-auto "
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
                    <h1 class="font">ระบบออกใบสั่งจ่ายยา</h1>
                </v-flex>
            </v-layout>
          
            <v-row justify="center">
                <v-col>
                 <v-form>
                     <div>
                     <v-flex md12>
                        <v-row justify="center">
                            <v-col cols="2" ></v-col>
                            <v-col cols="8"  align="center">
                             <v-data-table :headers="headers" :items ="order" :items-per-page="5"></v-data-table>
                           
                            </v-col>
                            <v-col cols="2">
                                
                                
                            </v-col>
                        </v-row>
                     </v-flex>
                     </div>
                 </v-form>
                </v-col>

            </v-row>

              <v-row justify="center">
                <v-col>
                    <v-form v-model="valid" ref="form">
                     <div>
                     <v-flex md12>
                        <v-row justify="center">
                            <v-col cols="3" ></v-col>
                            <v-col cols="5"  align="center">

                
                                <v-text-field class="font1 "
                                    outlined
                                    label="Prescription Number"
                                    v-model= "prescription_number"
                                    :rules="[(v) => !!v || 'Item is required']"
                                    required
                                    
                                ></v-text-field>
                              
                            </v-col>
                            <v-col cols="4">
                               <v-btn class="font1 " @click="getPN" depressed large color="primary">ค้นหาใบจ่ายยา</v-btn>
                                <!-- <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn> -->

                                
                            </v-col>
                        </v-row>
                     </v-flex>
                     </div>
                    </v-form>
                </v-col>

            </v-row>

            <v-row> 
                <v-col>
                    <div v-if="prescriptionNumberSelected == true"  class="font2">
                     <v-form v-model="valid" ref="form">
                        <v-flex md12>
                            <v-row> 
                                <v-col cols = "1" ></v-col>
                                <v-col cols = "10" >
                                   <div id ="printMe">
                                    <h1 id = 'text_center' class='content'>ใบสั่งจ่ายยา </h1>
                                    <h2 id = 'text_right' class='content1'>เลขที่ใบจ่ายยา : {{prescriptionNumber}}</h2>
                                    <br><br>
                                    <h2 id = 'text_left' class='content'>วันสั่งที่จ่ายยา : {{date}}</h2> 
                                    <h2 id = 'text_left' class='content'>National Id : {{NationalID}}</h2>
                                     <h2 id = 'text_left' class='content'>อาการ : {{symtom}}</h2>
                                    <h2 id = 'text_left' class='content'>ยา : {{Mname1}}</h2>
                                    <h2 id = 'text_left' class='content'>การบริโภค : {{typeName1}}</h2>
    
                                    <h2 id = 'text_left' class='content'>แพ้ยา : {{allergies}}</h2>
                                    <h2 id = 'text_left' class='content'>อาการแพ้ : {{reaction}}</h2>
                                     <h2 id = 'text_left' class='content'>แพทย์เจ้าของไข้ : {{dname}}</h2>
                                   </div>

                                <v-btn class="ma-2" tile  color="green" @click="print">
                                                  <v-icon center></v-icon> พิมพ์ใบเสร็จที่นี่
                                          </v-btn>

                                          <v-btn style="margin-left: 15px;" v-on:click="clear">clear</v-btn>

                                </v-col>

                               
                                <v-col cols = "1" >
                                
                                </v-col>
                            </v-row>
                    </v-flex>
                     </v-form>
                    </div>
                </v-col>
            </v-row>
        </v-card>
  </v-container>
    </div>
</template>
<script>
import http from "../http-common";
import VueHtmlToPaper from 'vue-html-to-paper';
import Vue from 'vue';

const options = {
        name: '_blank',
        specs: [
            'fullscreen=yes',
            'titlebar=yes',
            'scrollbars=yes'
        ],
        styles: [
            'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css',
            'https://unpkg.com/kidlat-css/css/kidlat.css'
        ]
    }

    Vue.use(VueHtmlToPaper, options);


export default {
    name:"prescription",
    data(){
        return{
            valid: false,
             menu2:true,
            prescriptionNumberSelected:false,
            order:[],
            headers: [
                { text: "National Id", value: 'ex.patient.nationalID'},
                { text: "Prescription Number", value: 'prescriptionNumber'}
            ],
            
            prescription_number:"",
           

            NationalID:"",
            date:"",
            prescriptionNumber:"",
            allergies:"",
            reaction:"",
            Mname1:"",
            typeName1:"",
            symtom:"",
              dname:"",
              
          
            
            
        };
    },

    methods:{
        print() {
            if(this.getPN == true) {
                    this.$htmlToPaper('printMe');
                    const options3 = { title: "Alert", size: "sm" };
                    this.$dialogs.alert("พิมพ์ใบสั่งจ่ายยาสำเร็จ", options3);
                     this.clear();
            }else if(this.getPN == false) {
                    const options4 = { title: "Alert", size: "sm" };
                    this.$dialogs.alert("พิมพ์ใบสั่งจ่ายยาไม่สำเร็จ", options4);
                     this.clear();
                }
        },
        clear(){
        this.$refs.form.reset();
        this.prescriptionNumberSelected = false;
        },
        getdoctorsOrder() {
        http
            .get("/doctororder")
            .then(response => {     
                this.order = response.data;
            
            })
            .catch(e => {
                // eslint-disable-next-line
                /* eslint-disable */
            console.log(e);
            });
        },

        getPN(){
            http
                .get("/prescription/" + this.prescription_number)
                .then(response => {
                console.log(this.prescriptionNumber);
                    console.log(response.data);  
                    if (response.data != null && response.data != "") {
                        this.prescriptionNumberSelected = true;
                        this.NationalID = response.data[0][1];
                        this.date = response.data[0][2];
                        this.prescriptionNumber = response.data[0][3];
                        this.symtom = response.data[0][4],
                        this.allergies =response.data[0][5];
                        this.reaction = response.data[0][6];
                        
                        for (let i = 0; i < (response.data).length; i++) {
                            if(i!=0 && i!= (response.data).length){
                                this.Mname1 += ",  ";
                            }
                            this.Mname1 += response.data[i][7];
                            
                        }
                        
                        // //this.Mname2 = response.data[1][8];
                        // this.typeName1 = response.data[0][9];
                        for (let i = 0; i < (response.data).length; i++) {
                            if(i!=0 && i!= (response.data).length){
                                this.typeName1 += ",  ";
                            }
                            this.typeName1 += response.data[i][8];
                            
                        }
                        this.dname = response.data[1][9];
                        console.log((response.data).length+"SIZE");
                    
                        console.log(response.data[0][1]);
                        this.getPN= true;
                         
                    }
                    else{
                        console.log(response.data+"*********EMPTY**********");
                        const options2 = { title: "Alert", size: "s" };
                        this.$dialogs.alert("NOT FOUND!",options2);
                        this.getPN= false;
                        this.clear();
                    }
                })
                .catch(e => {
                console.log(e);
                })
                
                this.submitted = true;
        },
        refreshList(){
            this.print()
            this.getPN();
        
        }, 
        
    },
    
        mounted(){
            this.getPN();
            this.getdoctorsOrder();
        }
   
     
};  
</script>

        
    


<style>
.background{
  background-image: linear-gradient(0deg, rgba(255, 255, 255, 0.1),rgba(255, 255, 255, 0.1)),url(../assets/173921.jpg);
  background-size: cover;;
  background-position: bottom;
  background-repeat: no-repeat;
  height: 100%;
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

.font2{
  font-family: 'Sarabun', sans-serif;
  font-size: 14px ;
  color: black;
  opacity: 0.7;
}
.content {
    padding:10px;
    clear:both;
    width:900px;
    margin:15px;
}
.content1 {
    padding:3px;
    clear:both;
    width:900px;
    margin:10px;
}
#text_center { text-align:center; }
#text_right { text-align:right; }
#text_left { text-align:left; }
</style>