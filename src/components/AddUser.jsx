import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const AddUser = () => {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [company, setCompany] = useState("");

    const navigate = useNavigate();

    const api = "https://jsonplaceholder.typicode.com/users";

    const addUser = async (e) => {

        e.preventDefault();

        try {

            const resp = await axios.post(api, {

                name,
                email,
                phone,
                company

            });

            console.log(resp.data);

            alert("User Added Successfully");

            navigate("/users");

        }
        catch (err) {

            console.log(err);

        }

    };

    return (

        <div className="container mt-4">

            <h2>Add User</h2>

            <form onSubmit={addUser}>

                <div className="mb-3">

                    <label>Name</label>

                    <input type="text" className="form-control"
                        onChange={(e) => setName(e.target.value) } value={name}/>

                </div>

                <div className="mb-3">

                    <label>Email</label>

                    <input type="email" className="form-control"
                        onChange={(e) => (setEmail(e.target.value))} value={email}/>

                </div>

                <div className="mb-3">

                    <label>Phone</label>

                    <input type="text" className="form-control"
                        onChange={(e) => (setPhone(e.target.value))} value={phone} />

                </div>

                <div className="mb-3">

                    <label>Company</label>

                    <input type="text" className="form-control"
                        onChange={(e) => (setCompany(e.target.value))} value={company}/>

                </div>

                <button className="btn btn-primary">
                    Add User
                </button>

            </form>

        </div>

    );
};

export default AddUser;