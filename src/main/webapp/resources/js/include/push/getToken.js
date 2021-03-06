let getToken = (device) => {
	// Initialize Firebase
    var config = {
          apiKey: "AIzaSyBm8HfRS1qzvn23KxVwDNLbZsOLHMAizBw",
            authDomain: "jachwi-haehyeop.firebaseapp.com",
            projectId: "jachwi-haehyeop",
            storageBucket: "jachwi-haehyeop.appspot.com",
            messagingSenderId: "767605215947",
            appId: "1:767605215947:web:d5c0f17a7829b9f0edc14e",
            measurementId: "G-KQVW6W128C"
    };
    firebase.initializeApp(config);
 
    const messaging = firebase.messaging();
 
    //token값 알아내기
    messaging.requestPermission()
        .then(function(){
            console.log("Have permission");
            return messaging.getToken();
        })
        .then(function(token){
            console.log(token);
            fetchToken(token,device);
            
        })
        .catch(function(arr){
            console.log("Error Occured");
        });
   
   messaging.onMessage((payload) => {
		navigator.serviceWorker.register('/firebase-messaging-sw.js');
		
		navigator.serviceWorker.ready.then(function(registration) {
			let title = payload.notification.title;
			let option = {
				body: payload.notification.body,
				icon: '/resources/image/logo-icon.png'
			};
			
			registration.showNotification(title, option);
		})
	})
}
