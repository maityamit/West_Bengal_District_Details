
var firebaseConfig = {
    apiKey: "AIzaSyA-hog-ZbKOXRujXwb5pPuvtlcaPPFMdBY",
    authDomain: "west-bengal-f0619.firebaseapp.com",
    databaseURL: "https://west-bengal-f0619-default-rtdb.firebaseio.com",
    projectId: "west-bengal-f0619",
    storageBucket: "west-bengal-f0619.appspot.com",
    messagingSenderId: "339582945233",
    appId: "1:339582945233:web:da79610f0cf35b4f4f02f0",
    measurementId: "G-R1BMWJFZN9"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();

  var database = firebase.database();


  function addItemstoList(name){
      var ul = document.getElementById("myItemList");
      var _name = document.createElement('li');

      _name.innerHTML = name;
      ul.appendChild(_name);
  }

  firebase.database().ref('/DistrictList').on('value',(snap)=>{
    var totalRecord =  snap.numChildren();
    console.log("Total Record : "+totalRecord);

    database.ref('/DistrictList').once('value', function(snapshot){
        snapshot.forEach(function(data){
          database.ref('/DistrictList/'+data.key+'/DISTNAME').on('value',(snap)=>{
            console.log(snap.val());


            addItemstoList(snap.val());


           


          });

        });
      })
      
   
    


  });

  

  

  

  
  





