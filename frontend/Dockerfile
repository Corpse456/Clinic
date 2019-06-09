FROM node:10-alpine

RUN mkdir /usr/front
WORKDIR /usr/front
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run start