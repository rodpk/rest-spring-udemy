import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { FiPower, FiEdit, FiTrash2 } from 'react-icons/fi';

import './styles.css';
import logoImage from '../../assets/logo.svg'
import api from '../../services/api'

export default function Books() {


    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');


    const [books, setBooks] = useState([]);

    const navigate = useNavigate();


    async function logout() {
        // aqui que chamaria oserviço p limpar o token
        localStorage.clear();
        navigate('/');
    }

    async function editBook(id) {
        try {
            navigate('/book/new/'+id);
        } catch(err) {
            alert(err);
        }
    }
    async function deleteBook(id) {
        try {

            await api.delete(`api/book/v1/${id}`, headers)

            setBooks(books.filter(book => books.id !== id))
        } catch(err) {
            alert('error deleting book')
        }
    }

    const headers = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    }

    // n coloquei params nos books
    // const params = {
    //     params: {
    //         page: 1,
    //         limit: 4,
    //         direction: 'asc'
    //     }
    // }

    useEffect(() => {
        api.get('api/book/v1', headers).then(response => {
            setBooks(response.data)
        })
    })

    return (
        <div className="book-container">
            <header>
                <img src={logoImage} alt="logo" />
                <span>Welcome, <strong>{username.toUpperCase()}</strong>!</span>
                <Link className="button" to="/books/new/0">Add new Book</Link>
                <button onclick = {logout()} type="button">
                    <FiPower size={18} color="#251fc5" />

                </button>
            </header>

            <h1>Registered Books</h1>

            <ul>
                {books.map(book => (
                    <li key = {book.id}>
                        <strong>Title: </strong>
                        <p>{book.title}</p>

                        <strong>Author: </strong>
                        <p>{book.author}</p>

                        <strong>Price: </strong>
                        <p>{Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(book.price)}</p>

                        <strong>Release Date: </strong>
                        <p>{Intl.DateTimeFormat('pt-BR').format(new Date (book.launchDate))}</p>

                        <button onClick={() => editBook(book.id)} type="button"> <FiEdit size={20} color="#251fc5" /> </button>
                        <button  onClick={() => deleteBook(book.id)} type="button"> <FiTrash2 size={20} color="#251fc5" /> </button>

                    </li>
                ))}
            </ul>
        </div>
    );
}