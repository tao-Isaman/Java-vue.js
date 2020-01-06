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
            <h1 class="font">ระบบลงทะเบียนผู้ป่วยใน</h1>
          </v-flex>
        </v-layout>

        <v-row justify="center">
          <v-col cols="10">
            <v-form v-model="valid" ref="form">
              <v-row justify="center">
                <v-col cols="10">
                  <v-text-field
                          class="font"
                          outlined
                          label="ชื่อ - นามสกุล"
                          v-model="Patient.name"
                          :rules="[(v) => !!v || 'กรุณากรอกชื่อ - นามสกุล']"
                          required
                  ></v-text-field>

                  <v-text-field
                          class="font"
                          outlined
                          label="รหัสบัตรประจำตัวประชาชน"
                          v-model="Patient.nationalID"
                          :rules="[(v) => !!v || 'กรุณากรอกรหัสบัตรประชาชน 13 หลัก']"
                          required
                  ></v-text-field>

                  <v-row justify="center">
                    <v-col cols="6">
                      <v-text-field
                              class="font"
                              outlined
                              label="น้ำหนัก"
                              v-model="Patient.weigth"
                              :rules="[(v) => !!v || 'กรุณาใส่น้ำหนักหน่วยกิโลกรัม kg.']"
                              required
                      ></v-text-field>
                    </v-col>

                    <v-col cols="6">
                      <v-text-field
                              class="font"
                              outlined
                              label="ส่วนสูง"
                              v-model="Patient.hight"
                              :rules="[(v) => !!v || 'กรุณาใส่ส่วยสูงเป็นเซนติเมตร cm.']"
                              required
                      ></v-text-field>
                    </v-col>
                  </v-row>

                  <v-textarea
                          class="font"
                          outlined
                          label="ที่อยู่"
                          v-model="Patient.address"
                          :rules="[(v) => !!v || 'กรุณาใส่ที่อยู่']"
                          required
                  ></v-textarea>

                  <v-textarea
                          class="font"
                          outlined
                          label="อาการเบื้องต้น"
                          v-model="Patient.initialSsym"
                          :rules="[(v) => !!v || 'กรุณาใส่อาการเบื้องต้น']"
                          required
                  ></v-textarea>

                  <v-row justify="center">
                    <v-col cols="3">
                      <v-select
                              class="font"
                              label="เพศ"
                              outlined
                              v-model="Patient.gender"
                              :items="genders"
                              item-text="name"
                              item-value="id"
                              :rules="[(v) => !!v || 'เลือกเพศกำเนิดของท่าน']"
                              required
                      ></v-select>
                    </v-col>

                    <v-col cols="4">
                      <v-select
                              class="font"
                              label="จังหวัด"
                              outlined
                              v-model="Patient.province"
                              :items="provinces"
                              item-text="name"
                              item-value="id"
                              :rules="[(v) => !!v || 'เลือกจังหวัดของท่าน']"
                              required
                      ></v-select>
                    </v-col>

                    <v-col cols="5">
                      <v-select
                              class="font"
                              label="สิทธิการรักษา"
                              outlined
                              v-model="Patient.benefit"
                              :items="benefits"
                              item-text="name"
                              item-value="id"
                              :rules="[(v) => !!v || 'เลือกสิทธิการรักษา']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row justify="center">
                    <v-col cols="20">
                      <v-btn x-large @click="saveVideoRental" :class="{ red: !valid, green: valid }">submit</v-btn>
                      <v-btn x-large style="margin-left: 15px;" @click="clear">clear</v-btn>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>


            </v-form>
          </v-col>
        </v-row>


      </v-container>
    </v-card>
  </div>
</template>

<script>

  import http from "../http-common";
  export default {
    name: "Patient",
    data() {
      return {
        Patient: {
          name : "",
          nationalID : "",
          weigth : "",
          hight : "",
          address : "",
          initialSsym : "",
          gender : "",
          province : "",
          benefit : ""
        },
        valid: false,
        customerCheck: false,
        customerName: "",
        benefits:[],
        genders:[],
        provinces:[]
      };
    },
    methods: {
      /* eslint-disable no-console */

      getGender() {
        http
                .get("/gender")
                .then(response => {
                  this.genders = response.data;
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
      },
      getProvince() {
        http
                .get("/province")
                .then(response => {
                  this.provinces = response.data;
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
      },

      getBenefit() {
        http
                .get("/benefit")
                .then(response => {
                  this.benefits = response.data;
                  console.log(response.data);
                })
                .catch(e => {
                  console.log(e);
                });
      },


      // function เมื่อกดปุ่ม submit
      saveVideoRental() {
        http
                .post(
                        "/patientPost",
                        this.Patient
                )
                .then(response => {
                  console.log(response);
                  alert("บันทึกสำเร็จ");
                  this.clear();
                })
                .catch(e => {
                  console.log(e);
                });
        console.log(this.Patient);
        http
                .get("/patient")
                .then(response => {
                  this.items = response.data;
                  console.log(this.items);
                })
                .catch(e => {
                  console.log(e);
                });
        this.submitted = true;
      },
      clear() {
        this.$refs.form.reset();
        this.customerCheck = false;
      },
      refreshList() {
        this.getGender();
        this.getProvince();
        this.getBenefit();
      }
      /* eslint-enable no-console */
    },
    mounted() {
      this.getGender();
      this.getProvince();
      this.getBenefit();


    }

  };
</script>
<style>
  .font {
    font-family: 'Prompt', sans-serif;
    font-size: 30px ;
    color: black;
  }
  .back {
    background-image:  url(../assets/g1.png);
    background-size: cover;
    background-blend-mode: multiply;
  }

</style>>
