-- liquibase formatted sql

-- changeset hussamoa:1683831159168-1
CREATE TABLE "input_payload" ("requestid" VARCHAR(10) NOT NULL, "methodidentifier" VARCHAR(50), "method_cofig" TEXT, CONSTRAINT "input_payload_pkey" PRIMARY KEY ("requestid"));

-- changeset hussamoa:1683831159168-2
CREATE TABLE "method_cofig" ("methodidentifier" VARCHAR(50) NOT NULL, "inputasoutput" BOOLEAN, "outputpredefined" BOOLEAN, CONSTRAINT "method_cofig_pkey" PRIMARY KEY ("methodidentifier"));

-- changeset hussamoa:1683831159168-3
CREATE TABLE "output_payload" ("requestid" VARCHAR(50), "outputpayload" TEXT);

-- changeset hussamoa:1683831159168-4
CREATE TABLE "output_predefined" ("methodidentifier" VARCHAR(50), "outputpayload" TEXT, "naming" VARCHAR(200));

-- changeset hussamoa:1683831159168-5
CREATE TABLE "output_predefined2" ("methodidentifier" VARCHAR(50), "outputpayload" TEXT);

-- changeset hussamoa:1683831159168-6
ALTER TABLE "input_payload" ADD CONSTRAINT "input_payload_methodidentifier_fkey" FOREIGN KEY ("methodidentifier") REFERENCES "method_cofig" ("methodidentifier") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset hussamoa:1683831159168-7
ALTER TABLE "output_payload" ADD CONSTRAINT "output_payload_requestid_fkey" FOREIGN KEY ("requestid") REFERENCES "input_payload" ("requestid") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset hussamoa:1683831159168-8
ALTER TABLE "output_predefined2" ADD CONSTRAINT "output_predefined2_methodidentifier_fkey" FOREIGN KEY ("methodidentifier") REFERENCES "method_cofig" ("methodidentifier") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset hussamoa:1683831159168-9
ALTER TABLE "output_predefined" ADD CONSTRAINT "output_predefined_methodidentifier_fkey" FOREIGN KEY ("methodidentifier") REFERENCES "method_cofig" ("methodidentifier") ON UPDATE NO ACTION ON DELETE NO ACTION;

