iconst sql = require('mssql');
const express = require('express');
const bodyParser = require('body-parser');

//STRING DO SEIJI
const connStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19190;User id=BD19190;Password=??';
//STRING DO TONHAO
// const connStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19162;User id=BD19162;Password=frasemalucadotonhao';
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
            //res.json(result.recordset);
            res.json('Requisição bem sucedida!');
        })
        .catch(err => res.json(err));
};

router.post('/main/:ra/:cod/:nota/:freq', (req, res) => {
    const ra = parseInt(req.params.ra);
    const disciplina = parseInt(req.params.cod);
    const nota = parseFloat(req.params.nota);
    const frequencia = parseFloat(req.params.freq);

    execSQLQuery(`INSERT INTO Resultados(RA,Cod,Nota,Frequencia) VALUES(${ra},${disciplina},${nota},${frequencia})`, res);
    execSQLQuery(`DELETE FROM Matriculas WHERE RA = ${ra}`, res);
});
