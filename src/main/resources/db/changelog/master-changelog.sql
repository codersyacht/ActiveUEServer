--changeset master.changelog:1

create table User_Details (userId varchar(50), password varchar(50), userToken varchar(5), primary key (userId), unique (userToken));

create table Method_Cofig(methodIdentifier varchar(50), inputasOutput boolean, outputPredefined boolean, userToken varchar(5) references 
User_Details(userToken) , primary key (methodIdentifier, userToken));

create table Input_Payload (requestId varchar(10), methodIdentifier varchar(50), inputPayload TEXT, userToken varchar(5), primary key (requestId), 
FOREIGN KEY (methodIdentifier, userToken) references Method_Cofig(methodIdentifier, userToken));

create table Output_Payload(requestId varchar(50) references Input_Payload(requestId), outputPayload TEXT, 
userToken varchar(5) references User_Details(userToken) ); 

create table Output_Predefined (methodIdentifier varchar(50) , outputPayload TEXT, userToken varchar(5), 
FOREIGN KEY (methodIdentifier, userToken) references Method_Cofig(methodIdentifier, userToken)); 