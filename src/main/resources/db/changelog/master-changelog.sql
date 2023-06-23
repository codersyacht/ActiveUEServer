--changeset master.changelog:1

create table Method_Cofig(methodIdentifier varchar(50), inputasOutput boolean, outputPredefined boolean, primary key (methodIdentifier));
create table Input_Payload (requestId varchar(10), methodIdentifier varchar(50) references Method_Cofig(methodIdentifier), inputPayload TEXT, primary key (requestId));
create table Output_Payload(requestId varchar(50) references Input_Payload(requestId), outputPayload TEXT ); 
create table Output_Predefined (methodIdentifier varchar(50) references Method_Cofig(methodIdentifier), outputPayload TEXT ); 

