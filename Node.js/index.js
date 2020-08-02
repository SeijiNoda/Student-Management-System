const sql = require('mssql');
const express = require('express');
const bodyParser = require('body-parser');

//STRING DO SEIJI
//const connStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19190;User id=BD19190;Password=???';
//STRING DO TONHAO
const connStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19162;User id=BD19162;Password=felicidadeeounicocaminhoquevaleapena';
//STRING DO NICOLINHO
// const connStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19192;User id=BD19192;Password=SENHADONICOLINHO';
sql.connect(connStr)
    .then(conn => global.conn = conn)
    .catch(err => console.log(err));

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

const router = express.Router();
router.get('/', (req, res) => res.json({ "Message": "API online"}));
app.use('/', router);

const port = 3000;
app.listen(port);
console.log("Active server");

const execSQLQuery = (command, res) => {
    global.conn.request().query(command)
        .then(result => {
            if(res.headersSent) return;
            //res.json({status: 'Requisição bem-sucedida!'});
            res.send(200, "Sucesso");
        })
        .catch(err => {
            //res.json({status: `Requisição mal-sucedida: ${err}`})
            if(res.headersSent) return;
            res.send(500, "Erro na requisicao");
        });
};

// router.get('/main/:ra/:cod/:nota/:freq', (req, res) => {
//     const ra = parseInt(req.params.ra);
//     const disciplina = parseInt(req.params.cod);
//     const nota = parseFloat(req.params.nota);
//     const frequencia = parseFloat(req.params.freq);

//     execSQLQuery(`INSERT INTO Resultado_ed(RA,CodDisciplina,Nota,Frequencia) VALUES(${ra},${disciplina},${nota},${frequencia})`, res);
//     execSQLQuery(`DELETE FROM Matricula_ed WHERE RA = ${ra} AND CODDISCIPLINA = ${disciplina}`, res);
// });
router.get('/main/:ra/:cod/:nota/:freq', (req, res) => {
    const ra = parseInt(req.params.ra);
    const disciplina = parseInt(req.params.cod);
    const nota = parseFloat(req.params.nota);
    const frequencia = parseFloat(req.params.freq);

    execSQLQuery(`INSERT INTO Resultado_ed(RA,CodDisciplina,Nota,Frequencia) VALUES(${ra},${disciplina},${nota},${frequencia})`, res);
    execSQLQuery(`DELETE FROM Matricula_ed WHERE RA = ${ra} AND CODDISCIPLINA = ${disciplina}`, res);

    //res.send(new String("SUCESSO"));
});
