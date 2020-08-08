const sql = require('mssql');
const express = require('express');
const bodyParser = require('body-parser');

const connStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19162;User id=BD19162;Password=felicidadeeounicocaminhoquevaleapena';
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

function execSQLQuery1(sqlQry, res) {
    global.conn.request().query(sqlQry)
        .then(result => {
            res.status(200).send(new String('Sucesso'))
            console.log('Requisicao bem sucedida!');
        })
        .catch(err =>  {
            res.json(new String('Operacao mal sucedida: ' + err))
            console.log('Requisicao deu erro!');
        });
};

router.get('/main/:ra/:cod/:nota/:freq', (req, res) => {
    const ra = parseInt(req.params.ra);
    const disciplina = parseInt(req.params.cod);
    const nota = parseFloat(req.params.nota);
    const frequencia = parseFloat(req.params.freq);

    execSQLQuery1(`INSERT INTO Resultado_ed(RA,CodDisciplina,Nota,Frequencia) VALUES(${ra},${disciplina},${nota},${frequencia})`, res);
});
