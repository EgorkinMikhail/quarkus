CREATE TABLE ROOM (
   ROOM_ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   NAME VARCHAR(255),
   ROOM_NUMBER VARCHAR(255),
   BED_INFO VARCHAR(255),
   CONSTRAINT pk_room PRIMARY KEY (ROOM_ID)
);