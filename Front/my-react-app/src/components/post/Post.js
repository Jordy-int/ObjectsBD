import * as React from 'react';

import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { ListItemAvatar } from '@mui/material';

export default function Post() {

  const [object, setObject] = useState({});
  const [objectData, setObjectData] = useState({});

  const { id } = useParams();

  const [count, setCount] = useState(0);

  const fetchObjects = async () => {
    try {
      const response = await fetch(`http://localhost:8080/objects/${id}`, {
        method: 'GET'
      })
      const data = await response.json();

      console.log(data)

      setObject(data);
      if (data.data === null) {
        setObjectData(" " , " ")
      }else{
        setObjectData(data.data);
      }
    } catch (error) {
      console.log("Error fetch objetos")
    }
  }

  function addField() {
    const newCount = count + 1;

    setCount(newCount)
    setObjectData({ ...objectData, [`Campo${newCount}`]: `Campo${newCount}` })

    console.log(objectData)
    console.log(object)
  }
  

  function deleteInput() {
    const keys = Object.keys(objectData);


    if (keys.length === 0) {
      return;
    }


    if (count > 0) {
      const newCount = count - 1;
      setCount(newCount)
    }

    const lastKey = keys[keys.length - 1];

    const newObject = { ...objectData }

    delete newObject[lastKey]

    setObjectData(newObject)
  }


  function handleSubmit(e) {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);

    const formJson = Object.fromEntries(formData.entries());

    const json = {
      name: formJson.name,
      data: createJson(formJson)
    }

    fetchPut(json)

  }

  async function fetchPut(json) {

    try {
      const response = await fetch(`http://localhost:8080/objects`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify( json )

      })

    } catch (error) {
      console.log("error al enviar " + error)
    }
  }

  function createJson(formJson) {
    let listKey = [];
    let listValue = [];
    const object = {};

    Object.entries(formJson).forEach(([key, value]) => {

      if (/key/.test(key)) {
        listKey.push(value)
      }
      if (/value/.test(key)) {
        listValue.push(value)
      }

    })


    for (let index = 0; index < listKey.length; index++) {
      object[listKey[index]] = listValue[index]
    }

    return object;

  }

  // Ejecuta al momento de cargar el Componente
  useEffect(() => {
    fetchObjects();

  }, [id]);

  return (
    <>

      <form method='post' onSubmit={handleSubmit}>


        <div className="mb-3">
          <label htmlFor="formGroupExampleInput" className="form-label">Nombre</label>
          <input type="text" defaultValue={''} name='name' className="form-control" id="formGroupExampleInput" />
        </div>
        <div className="mb-3">
          <label className="form-label">Data</label>
        </div>
        {Object.entries(objectData).map(([key, value]) =>
          <div key={key}>
            <input defaultValue={''} type='text' name={"key " + key} placeholder={key} />
            <input defaultValue={''} type='text' name={"value " + value} placeholder={value} />
          </div>
        )
        }
        <button type='submit' >Revisar info del formulario</button>
        <button type='reset' >Reiniciar</button>

        <button type='button' onClick={addField} >Agregar Campo</button>
        <button type='button' onClick={deleteInput} >Borrar Campo</button>
      </form>
    </>
  );
}