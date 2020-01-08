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
                        <h1 class="font">ระบบนำจำหน่ายผู้ป่วย</h1>
                    </v-flex>
                </v-layout>
                <v-row justify="center">
                    <v-col cols="10">
                        <v-form v-model="valid" ref="form">
                            <v-row>
                                <v-col cols="10">
                                    <v-text-field
                                            outlined
                                            label="รหัสบัตรประชาชน"
                                            v-model="discharge.nationalId"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-text-field>
                                    <p v-if="patientCheck != ''">Patient Name : {{patientName}}</p>
                                </v-col>
                                <v-btn @click="findPatient" :class="{ green: valid }">ค้นหา</v-btn>
                            </v-row>

                            <div v-if="patientCheck">
                                <h1 class="display-2 font-weight mb-3">สถานะผู้ป่วยก่อนนำจำหน่าย</h1>

                                <v-col cols="10">
                                    <v-select
                                            label="ระดับความรู้สึกตัว"
                                            outlined
                                            v-model="discharge.sensoriumId"
                                            :items="sensorium"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                    <v-select
                                            label="ความสามารถในการดูแลตนเอง"
                                            outlined
                                            v-model="discharge.selfcareId"
                                            :items="selfcare"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                    <v-select
                                            label="ประเภทการนำจำหน่าย"
                                            outlined
                                            v-model="discharge.distributionType"
                                            :items="distributionType"
                                            item-text="name"
                                            item-value="id"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-select>
                                    <v-textarea
                                            outlined
                                            label="หมายเหตุ"
                                            v-model="discharge.note"
                                            :rules="[(v) => !!v || 'Item is required']"
                                            required
                                    ></v-textarea>
                                </v-col>
                                <v-row justify="center">
                                    <v-col cols="20">
                                        <v-btn @click="saveDischarge" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                                        <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                                    </v-col>
                                </v-row>
                            </div>
                        </v-form>
                    </v-col>
                </v-row>




            </v-container>
        </v-card>
    </div>
</template>

<script>
    import http from "../http-common";
    // eslint-disable-next-line no-unused-vars

    export default {
        name: "discharge",
        data() {
            return {
                discharge: {
                    nationalId: '',
                    sensoriumId: '',
                    selfcareId: '',
                    distributionType: '',
                    note: ''
                },
                valid: false,
                patientCheck: false,
                patientName: "",
                sensorium:[],
                selfcare:[],
                distributionType:[],
                note: ''
            };
        },
        methods: {
            /* eslint-disable no-console */
            getSensorium() {
                http
                    .get("/sensorium")
                    .then(response => {
                        this.sensorium = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            getSelfcare() {
                http
                    .get("/selfcare")
                    .then(response => {
                        this.selfcare = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            getDistributionType() {
                http
                    .get("/distributionType")
                    .then(response => {
                        this.distributionType = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            /* eslint-disable */
            findPatient() {
                http
                    .get("/patient/"+this.discharge.nationalId)
                    .then(response => {
                        console.log(response);
                        if (response.data != null) {
                            this.patientName = response.data.name;
                            this.patientCheck = response.status;
                        } else {
                            this.clear()
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.submitted = true;
            },

            saveDischarge() {
                http
                    .post("/adddischarge/"+
                        this.discharge.nationalId+
                        "/"+this.discharge.sensoriumId+
                        "/"+this.discharge.selfcareId+
                        "/"+this.discharge.distributionType+
                        "/"+this.discharge.note+
                        "/",this.discharge)
                    .then(response => {
                        console.log(response);
                        alert("บันทึกสำเร็จ");
                        this.clear();
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.submitted = true;
            },
            clear() {
                this.$refs.form.reset();
                this.patientCheck = false;
            },
            refreshList() {
                this.getSensorium();
                this.getSelfcare();
                this.getDistributionType();
            }
            /* eslint-enable no-console */
        },
        mounted() {
            this.getSensorium();
            this.getSelfcare();
            this.getDistributionType();

        }
    };
</script>
<style>
    .font {
        font-family: 'Prompt', sans-serif;
        font-size: 30px ;
        color: white;
    }
    .back {
        background-image: linear-gradient(0deg, rgba(255, 0, 150, 0.3), rgba(255, 0, 150, 0.3)), url(../assets/g1.png);
        background-size: cover;
        background-blend-mode: multiply;
    }

</style>>