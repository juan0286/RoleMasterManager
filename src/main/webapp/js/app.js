(function () {
    var app = angular.module('cdgApp', ["ngResource", "ngRoute"]);

    app.controller('ListaController', ['$scope', '$http',
        function ($scope, $http) {

            $scope.getLista = function (nameList) {

                if (nameList == "bonosExp")
                    var path = "getAllBonosExp"+ '/' + $scope.id_pj;
                if (nameList == "solicitudes")
                    var path = "getSolicitudes";
                if (nameList == "bonosExp")
                    var path = "getAllBonosExp";
                if (nameList == "bonosExp")
                    var path = "getAllBonosExp";
                

                $("#spinner").addClass("is-active");
                $scope.lista = [];
                $http.get('/RoleMasterManager/' + path )
                        .then(function (data) {
                            $("#spinner").removeClass("is-active");
                            $scope.lista = data.data;

                        }, function (error) {
                            $("#spinner").removeClass("is-active");
                            $scope.msg = 'Se ha producido un error al traer los datos';
                            var notification = document.querySelector('.mdl-js-snackbar');
                            notification.MaterialSnackbar.showSnackbar(
                                    {
                                        message: 'Error cargando los datos'
                                    }
                            );
                        });
            }

        }]);

    app.controller('BonosExpController', ['$scope', '$http',
        function ($scope, $http) {

            $scope.getBonosExp = function () {
                $("#spinner").addClass("is-active");
                $scope.bonos = [];
                $http.get('/RoleMasterManager/getAllBonosExp/1')
                        .then(function (data) {
                            $("#spinner").removeClass("is-active");
                            $scope.bonos = data.data;

                        }, function (error) {
                            $("#spinner").removeClass("is-active");
                            $scope.msg = 'Se ha producido un error leyendo los bonos existentes';
                            var notification = document.querySelector('.mdl-js-snackbar');
                            notification.MaterialSnackbar.showSnackbar(
                                    {
                                        message: 'Error cargando los Bonos'
                                    }
                            );
                        });
            }

            $scope.addBono = function () {
                $('#addBtn').attr("disabled", true);
                $http.post('/RoleMasterManager/addBonoExp',
                        {
                            id_pj: $scope.id_pj,
                            bono: $scope.bono,
                            motivo: $scope.motivo

                        }
                ).then(function (data) {
                    //$scope.msg = 'Bono creado Exitosamente';
                    $scope.getLista($scope.nameList);
                    var notification = document.querySelector('.mdl-js-snackbar');
                    notification.MaterialSnackbar.showSnackbar(
                            {
                                message: 'Bono creado Exitosamente'
                            }
                    );
                    $scope.motivo = "";
                    $scope.bono = "";
                    $('#addBtn').removeAttr("disabled");
                }, function (error) {
                    $scope.msg = 'Se ha producido un error al guardar el bono';
                    var notification = document.querySelector('.mdl-js-snackbar');
                    notification.MaterialSnackbar.showSnackbar(
                            {
                                message: 'Error Guardando el bono'
                            }
                    );
                    $('#addBtn').removeAttr("disabled");
                });
            }
        }]);
//    
//    var app = angular.module('films', ["ngResource"]);
//
//    app.controller('FilmsController', ['$scope', '$http',
//        function ($scope, $http) {
//            $scope.getFilms = function () {
//                $http.get('/films').success(function (data) {
//                    $scope.films = data;
//                });
//            }
//
//            $scope.addFilm = function () {
//                $http.post('/films',
//                        {
//                            title: $scope.title,
//                            year: $scope.year,
//                            director: $scope.director
//                        }
//                ).success(function (data) {
//                    $scope.msg = 'Pelicula creada correctamente';
//                    $scope.getFilms();
//                }).error(function (data) {
//                    $scope.msg = 'Se ha producido un error';
//                });
//            }
//        }]);


})();