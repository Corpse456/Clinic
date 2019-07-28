FROM node:10-alpine

RUN mkdir -p /front
WORKDIR /usr/front
COPY package*.json ./
COPY package.json /front/package.json
COPY package-lock.json /front/package-lock.json
RUN npm install
COPY . .

ENTRYPOINT ["npm", "run", "start"]