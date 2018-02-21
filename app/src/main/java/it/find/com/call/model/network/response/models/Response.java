package it.find.com.call.model.network.response.models;

import java.util.ArrayList;

/**
 * Created by Bruno on 04-Feb-18.
 */

public class Response {

    private Success success;
    private Error error;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public class Success {

        protected String code;
        protected Data data;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public Success() {
        }

        public Success(String code, Data data) {
            this.code = code;
            this.data = data;
        }

        public class Data {
            protected ArrayList<ArrayList<String>> records;
            protected ArrayList<Fields> fields;
            protected String value;

            public String getValue() { return value; }

            public void setValue(String value) { this.value = value; }

            public ArrayList<ArrayList<String>> getRecords() {
                return records;
            }

            public void setRecords(ArrayList<ArrayList<String>> records) {
                this.records = records;
            }

            public ArrayList<Fields> getFields() {
                return fields;
            }

            public void setFields(ArrayList<Fields> fields) {
                this.fields = fields;
            }



            public Data() {
            }

            public Data(ArrayList<ArrayList<String>> records, ArrayList<Fields> fields, String value) {
                this.records = records;
                this.fields = fields;
                this.value = value;
            }

            public class Fields {
                protected String schema;
                protected String name;
                protected String type;
                protected String table;

                public String getSchema() {
                    return schema;
                }

                public void setSchema(String schema) {
                    this.schema = schema;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getTable() {
                    return table;
                }

                public void setTable(String table) {
                    this.table = table;
                }

                public Fields() {
                }

                public Fields(String schema, String name, String type, String table) {
                    this.schema = schema;
                    this.name = name;
                    this.type = type;
                    this.table = table;
                }
            }
        }
    }

    public class Error {

        protected String code;
        protected String extras;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getExtras() {
            return extras;
        }

        public void setExtras(String extras) {
            this.extras = extras;
        }

        public Error() {
        }

        public Error(String code, String extras) {
            this.code = code;
            this.extras = extras;
        }



    }
}
