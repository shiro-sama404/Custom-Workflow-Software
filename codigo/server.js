const express = require("express")
const app = express()

var path = require('path');

const handlebars = require('express-handlebars')
const bodyParser = require("body-parser")

app.engine('handlebars', handlebars.engine({ defaultLayout: 'main' }));

app.set('view engine', 'handlebars')

app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json());

app.set('views', path.join(__dirname, 'view'));

app.use(express.static(path.join(__dirname, 'public')));

var log = false;

// getters
app.get("/", function(req, res, next) {

    try {
        if (log) {

            return res.render(req.params.tela, {
                styles: [
                    '/css/layout.css',
                    `/css/${req.params.tela}.css`
                ],
                scripts: [
                    '/js/menu.js',
                    `/js/${req.params.tela}.js`
                ]
            });
        }else {

            return res.render('login', {
                styles: [
                    '/css/login.css'
                ]
            });
        }
    } catch (erro) {
        next(erro);
    }
});

app.get("/:tela", function(req, res, next) {

    try {
        if (log) {
            if (typeof req.params.tela === "undefined") {

                return res.render('previa', {
                    styles: [
                        '/css/layout.css',
                        '/css/previa.css'
                    ],
                    scripts: [
                        '/js/layout.js',
                        '/js/previa.js'
                    ]
                });
                
            } else {

                return res.render(req.params.tela, {
                    styles: [
                        '/css/layout.css',
                        `/css/${req.params.tela}.css`
                    ],
                    scripts: [
                        '/js/menu.js',
                        `/js/${req.params.tela}.js`
                    ]
                });
            }
        } else {

            return res.render('login', {
                styles: [
                    '/css/login.css'
                ]
            });
        }
    } catch (erro) {
        next(erro);
    }
});

//                               MÉTODOS POST
app.post("/previa", function(req, res){

    log = true;

    return res.render('previa',{
        styles:
        [
            '/css/layout.css',
            '/css/previa.css'
        ],
        scripts:
        [
            '/js/menu.js',
            '/js/previa.js'
        ]
    })
});

app.use(function(err, req, res, next){
    //console.error(err.stack);
    return res.status(404).render('notFound', {
        styles: [
            '/css/notFound.css'
        ]
    });
});

app.listen(80, function(){console.log('Server on');});