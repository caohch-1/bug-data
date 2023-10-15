FROM node:13.3.0

RUN mkdir -p /app
WORKDIR /app

ADD ./node_modules /app/node_modules
ADD ./package.json /app/
# RUN npm install
RUN npm install async

ADD ./rest-service-external.js /app/
CMD [ "npm", "start" ]

EXPOSE 16100