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
                 <v-form v-model="valid" ref="form">
                     <div>
                     <v-flex md12>
                        <v-row justify="center">
                            <v-col cols="3" ></v-col>
                            <v-col cols="6"  align="center">
                                <v-text-field class="font1 "
                                    outlined
                                    label="National ID (รหัสบัตรประชาชน)"
                                    v-model="NationalID"
                                    :rules="[(v) => !!v || 'Item is required']"
                                    required
                                    
                                ></v-text-field>

                                <p v-if="prescriptionNumberCheck != ''">Name : {{Name}}</p>

                            </v-col>
                            <v-col cols="3">
                                <v-btn class="font1 " @click="getNationalID" depressed large color="primary">ค้นหา</v-btn>
                                
                            </v-col>
                             

                        </v-row>
                     </v-flex>
                     </div>
                 </v-form>
                </v-col>
               



            </v-row>

            <v-row> 
                <v-col>
                    <div v-if="prescriptionNumberCheck"  class="font2">
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
                                    <h2 id = 'text_left' class='content'>ชื่อ : {{Name}}</h2>
                                     <!-- <h2> 
          <li v-for="(item) in Mname" :key="item.id">
              {{item[1]}}  ราคา   
          </li>
             </h2> -->
                                    <h2 id = 'text_left' class='content'>ยา : {{Mname1}}</h2>
                                    <h2 id = 'text_left' class='content'>     {{Mname2}}</h2>
                                    <h2 id = 'text_left' class='content'>การบริโภค :{{typeName1}}</h2>
                                     <h2 id = 'text_left' class='content'>        {{typeName2}}</h2>
                                   
                                   
                                    <h2 id = 'text_left' class='content'>แพ้ยา : {{allergies}}</h2>
                                    <h2 id = 'text_left' class='content'>อาการแพ้ : {{reaction}}</h2>
                                    <h2 id = 'text_left' class='content'>แพทย์ผู้รักษา : {{dname}}</h2>
                                   </div>

                                <v-btn class="ma-2" tile  color="green" @click="print">
                                                  <v-icon left></v-icon> พิมพ์ใบเสร็จที่นี่
                                          </v-btn>
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
            prescriptionNumberCheck:false,
            NationalID:"",
            prescriptionNumber:"",
            patientName:"",
            typeName1:"",
             typeName2:"",
            dname:"",
            Mname:[],
            Mname2:"",
            Mname1:"",
            allergies:"",
            reaction:"",
            date:"",
            Name:""
        };
    },

    methods:{

    print() {
      this.$htmlToPaper('printMe');
      this.clear()
    },

    getNationalID(){
            http
            .get("/prescriptionNumber/"+this.NationalID)
            .then(response => {
            // eslint-disable-next-line
            /* eslint-disable */
                console.log(response);
                if (response.data != null) {
                    this.prescriptionNumberCheck = response.NationalID;
                    this.prescriptionNumberCheck = response.status;
                     this.date = response.data[0][1];
                     this.allergies =response.data[0][2];
                    this.reaction = response.data[0][3];
                     this.prescriptionNumber = response.data[0][4];
                    this.Name = response.data[0][5];
                    this.NationalID = response.data[0][6];
                    this.Mname1 = response.data[0][8];
                    this.Mname2 = response.data[1][8];
                    this.typeName1 = response.data[0][9];
                    this.typeName2 = response.data[1][9];
                    this.dname= response.data[0][10];
                   
                    
                    //  for (i = 0; i < response.data.length; i++) {
                    //     this.Mname[i] = response.data[i][0];   
                    // }
                } else if(response.data == null) {
                    this.clear()
                     console.log(e);
                
                const options2 = { title: "Alert", size: "s" };
                this.$dialogs.alert("National Id is Not Corect!",options2);
                this.clear()
               
                }           
                console.log(response.data[0][1]);
            })
            // .catch(e => {
            // console.log(e);
                
            //     const options2 = { title: "Alert", size: "s" };
            //     this.$dialogs.alert("National Id is Not Corect!",options2);
            //     this.clear()
            // })
            
            this.submitted = true;
        }
    },
    clear(){
      this.$refs.form.reset();
    },
    refreshList(){
      this.getNationalID();
      this.print();
     
    }, 
    mounted(){
        this.getNationalID();

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