$(document).ready(function(){
   
  // solicitud para listar 

   $('#listar').on('click', function(){
      let tabla= document.querySelector('#tabla')
      tabla.innerHTML=''
      $.ajax({

          url:"http://localhost:8080/listarCelular",
          type: "GET",
          datatype: "JSON",
          success: function(salida){
              tabla.innerHTML='';
              for(i=0;i< salida.length;i++ ){
                  tabla.innerHTML += '<tr><td>'+ salida[i].nit+
                  '</td><td>'+ salida[i].marca+
                  '</td><td>'+ salida[i].tamano+
                  '</td><td>'+ salida[i].capacidad+
                  '</td><td>'+ salida[i].precio+
                  '</td><tr>';

              }
          },
          error:function(xhr, textStatus, errorThrown){

          }

      })
  });

  


     // funcion crear celular 
  $('#crear').on('click',function(){

   let datos={
      nit:parseInt($('#NIT').val()),
      marca: $('#Marca').val(),
      tamano:parseFloat($('#tamaño').val()),
      capacidad:$('#capacidad').val(),
      precio:parseFloat($('#precio').val())
   }
   let DatosEnviados = JSON.stringify(datos);
   $.ajax({
      url: "http://localhost:8080/agregarCelular",
      type: "POST",
      data: DatosEnviados,
      contentType: "application/JSON",
      datatype: 'JSON',
      success: function(respuesta) {
         
            alert('El celular se ha creado correctamente.');
            console.log(respuesta)
            $('#exampleModal').modal('hide');

           
        
      }, error:function(xhr, textStatus, errorThrown){

      }
      
  })

  });



    
// metodo para buscar por medio de nit //
    

   $('#buscarBtn').on('click', function() {
      var nit = $('#nitInput').val();
      // Realizar la solicitud AJAX para obtener la información del NIT
      $.ajax({
        url: 'http://localhost:8080/buscarCelular/' + nit,
        type: 'GET',
        dataType: 'json',
        success: function(respuesta) {
          if (respuesta) {
            // Mostrar la información del NIT en el modal
            var infoHtml = '<p>Marca: ' + respuesta.marca + '</p>' +
              '<p>Tamaño: ' + respuesta.tamano + '</p>' +
              '<p>Capacidad: ' + respuesta.capacidad + '</p>' +
              '<p>Precio: ' + respuesta.precio + '</p>';
            $('#infoModalBody').html(infoHtml);

            // Mostrar el modal con la información de la solicitud
            $('#infoModal').modal('show');
          } else {
            alert('No se encontró el NIT');
          }
        },
        error: function(xhr, textStatus, errorThrown) {
          alert('Error al buscar el NIT: ' + errorThrown);
        }
      });
    });



 
    //metodo para modificar mediante la busqueda de un nit //


    $('#buscar').on('click',function(){
      
      var nit= $('#nitModi').val();

      $.ajax({
         url:'http://localhost:8080/buscarCelular/' + nit,
         type:'GET',
         dataType:'json',
         success : function(respuesta){
            if(respuesta){
               $('#marcaInput').val(respuesta.marca);
               $('#tamanoInput').val(respuesta.tamano);
               $('#capacidadInput').val(respuesta.capacidad);
               $('#precioInput').val(respuesta.precio);
            
            $('#modificar').modal('show');
            $('#modificar').data('nit',nit);
            $('#modificarModal').modal('hide');

            }else{
               alert("no se encontro el celular")
            }
         },
         error: function(xhr) {
            if(xhr.status === 404)
            alert('Error al buscar el NIT: ');
          }
      });

    });

    $('#guardar').on('click', function() {
      

      var nit = $('#nitModi').val();
      var marca = $('#marcaInput').val();
      var tamano = $('#tamanoInput').val();
      var capacidad = $('#capacidadInput').val();
      var precio = $('#precioInput').val();
  
      // Crear un objeto con los datos modificados
      var datos = {
        
        marca: marca,
        tamano: tamano,
        capacidad: capacidad,
        precio: precio
      };
  
      // Realizar la solicitud AJAX para actualizar los datos del celular
      $.ajax({
        url: 'http://localhost:8080/actualizarCelular/' + nit,
        type: 'PUT',
        data: JSON.stringify(datos),
        contentType: 'application/JSON',
        dataType: 'JSON',
        success: function(respuesta) {
          alert('Celular modificado exitosamente');
          $('#modificar').modal('hide');
          
        },
        error: function(xhr) {
         if(xhr.status === 404)
          alert('Error al modificar el celular: ' + nit);
        }
      });
   
  });


  // solicitud para eliminar un celular por medio del  nit 
    $('#Eliminar').on('click', function() {
      let nit=parseInt($('#eliNIT').val());
      $.ajax({

        url:"http://localhost:8080/eliminarCelular/"+nit,
        type:"DELETE",
        success: function(respuesta){
          alert("el celular se elimino correctamente");
          location.reload();
          $('#eliminarModal').modal('hide');
        },
        error: function(xhr,status,error){
           var errorMessager= xhr.status +':'+ xhr.textStatus;
           alert("error al eliminar"+errorMessager)
        }
      }) 
      

        
      });

     
     
      
      
      
      

});

$('#exampleModal .close').on('click', function() {
   $('#exampleModal').modal('hide');
});

