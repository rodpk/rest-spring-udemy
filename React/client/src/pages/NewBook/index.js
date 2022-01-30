import React, { useState} from 'react';
import { useNavigate, Link, useParams } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/logo.svg';
import {FiArrowLeft} from 'react-icons/fi'
import api from '../../services/api'

export default function NewBook() {


    const {bookId} = useParams();

    const [id, setId] = useState(null);
    const [author, setAuthor] = useState('');
    const [launchDate, setLaunchDate] = useState('');
    const [price, setPrice] = useState('');
    const [title, setTitle] = useState('');

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken')


    const navigate = useNavigate();
    
    async function createNewBook(e) {
        e.preventDefault();

        const data = {
            title, 
            author, 
            launchDate, 
            price,
        }

        const headers = {
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }

        try {
            const response = await api.post('api/book/v1/', data, headers);
            navigate('/books');
        } catch(err) {
            alert('Error while recording book, try again! E: ' + err);
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="logo" />
                    <h1>Add new book</h1>

                    <p>Enter the book information and click on 'add'! #### {bookId}</p>
                    <Link className="back-link" to = "/books"> 
                        <FiArrowLeft size={16} color = "251fc5"/> 
                        Home
                    </Link>
                </section>

                <form onSubmit={createNewBook}>
                    <input value={title} onChange={e => setTitle(e.target.value)} placeholder="Title" />
                    <input value={author} onChange={e => setAuthor(e.target.value)} placeholder="Author" />
                    <input value={launchDate} onChange={e => setLaunchDate(e.target.value)} type="Date" />
                    <input value={price} onChange={e => setPrice(e.target.value)} placeholder="Price" />

                    <button className="button" type="submit">Add</button>
                </form>
            </div>
        </div>

    );

}