

var async = require('async');


var sleep = function(array, callback) {
  'use strict';


  var results = [];
  var tmpArray = array.slice(0);
  var isEnd = false;

  var run = function() {
    var tmp = null;
    if (tmpArray.length > 3) {
      tmp = tmpArray.splice(0, 3);
    } else {
      tmp = tmpArray;
      isEnd = true;
    }
    async.mapLimit(tmp, 2, function(item, itemCallback) {
      console.log('Enter: ' + item.name);
      setTimeout(function() {
        console.log('Handle: ' + item.name);
        itemCallback(false, item.name + '!!!');
      }, item.delay);
    }, function(err, data) {
      console.log(data);
      results = results.concat(data);
      if (isEnd) {
        callback(false, results);
      } else {
        console.log('Sleep 5s');
        setTimeout(run, 5000);
      }
    });
  };

  run();
};



var arr = [
  {name:'mocksleep', delay:2000}
];

// sleep(arr, function(error, data) {
//   'use strict';
//   console.log(data);
// });


//http server

var http = require('http');
var url = require('url');

var counter = 0;

http.createServer(function (req, res) {

  sleep(arr, function(error, data) {

    console.log("-------service external----------");

    var req_url = url.parse(req.url, true);
    var params = req_url.query;
    console.log(params);

    res.writeHead(200,{
      "Content-Type":'application/json',
      'charset':'utf-8',
      'Access-Control-Allow-Origin':'*',
      'Access-Control-Allow-Methods':'PUT,POST,GET,DELETE,OPTIONS'
    });

    if(req_url.pathname.indexOf('greeting_async') > -1){

        console.log("-------greeting_async----------");
        var options = {
            hostname: 'ts-inside-payment-service',
            port: '18673',
            path: '/hello1_callback?result=true'
          };
        function handleResponse(response) {
          var serverData = '';
          response.on('data', function (chunk) {
            serverData += chunk;
          });
          response.on('end', function () {
            console.log("Response Status:", response.statusCode);
            console.log("Response Headers:", response.headers);
            console.log(serverData);
          });
        }
        http.request(options, function(response){
          handleResponse(response);
        }).end();
    }else{
        // var resObj = {
        //   id: counter++,
        //   result: (params.cal < 100 && params.cal > 0)
        // };
        //
        // // res.writeHead(200,{
        // //   "Content-Type":'text/plain',
        // //   'charset':'utf-8',
        // //   'Access-Control-Allow-Origin':'*',
        // //   'Access-Control-Allow-Methods':'PUT,POST,GET,DELETE,OPTIONS'
        // // });
        //
        // res.write(JSON.stringify(resObj));
        console.log("-------greeting_sync----------");
        var resObj = true;
        res.write(JSON.stringify(resObj));
    }

    res.end();
    

  });
  
  
}).listen(16100);









