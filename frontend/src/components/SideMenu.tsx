'use client'
import React, { useState } from 'react'
import Link from 'next/link'
import { useAuth } from '../hooks/useAuth';
import LogoutButton from './LogoutButton';

const menuItems = [
  { path: '/', label: 'Home' },
  { path: '/login', label: 'Login' },
  { path: '/contact', label: 'Contact' },
  { path: '/paysheet', label: 'Nómina' }
];

const SideMenu = () => {

  const {user} = useAuth();

  return (
    <div className={`fixed top-0 left-0 w-64 h-screen bg-gray-800 text-white z-20`}>
      <div className="flex items-center justify-between px-4 py-2">
        <h2 className="text-xl font-bold">Sistema de Facturación</h2>
      </div>
      {

          user !== null && <div className="block w-full px-4 py-2 my-5">
            <div className='font-black w-full'>Bienvenid@</div>
            <div className='w-full'>{user.name}</div>
          </div>
      }
      <nav className="mt-4">
        <Link key={'/'} href={'/'} className="text-gray-300 hover:text-gray-200 px-4 py-2 flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"/>
          </svg>
          Home
        </Link>
        { user === null &&
          <Link key={'/login'} href={'/login'} className="text-gray-300 hover:text-gray-200 px-4 py-2 flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
            </svg>
            Login
          </Link>
        }
        { user?.role === "CUSTOMER" &&
          <Link key={'/customer/purchase'} href={'/customer/purchase'} className="text-gray-300 hover:text-gray-200 px-4 py-2 flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeWidth="2" d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6" />
              <circle cx="20" cy="21" r="1" strokeWidth="2" />
              <circle cx="9" cy="21" r="1" strokeWidth="2"/>
            </svg>
            Mis compras
          </Link>
        }
        {
          user?.role === "EMPLOYEE" && <Link  href={'/employee'} className="text-gray-300 hover:text-gray-200 px-4 py-2 flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 7.5V6.108c0-1.135.845-2.098 1.976-2.192.373-.03.748-.057 1.123-.08M15.75 18H18a2.25 2.25 0 0 0 2.25-2.25V6.108c0-1.135-.845-2.098-1.976-2.192a48.424 48.424 0 0 0-1.123-.08M15.75 18.75v-1.875a3.375 3.375 0 0 0-3.375-3.375h-1.5a1.125 1.125 0 0 1-1.125-1.125v-1.5A3.375 3.375 0 0 0 6.375 7.5H5.25m11.9-3.664A2.251 2.251 0 0 0 15 2.25h-1.5a2.251 2.251 0 0 0-2.15 1.586m5.8 0c.065.21.1.433.1.664v.75h-6V4.5c0-.231.035-.454.1-.664M6.75 7.5H4.875c-.621 0-1.125.504-1.125 1.125v12c0 .621.504 1.125 1.125 1.125h9.75c.621 0 1.125-.504 1.125-1.125V16.5a9 9 0 0 0-9-9Z" />
            </svg>

            </svg>
            Nómina
          </Link>
        }
      </nav>
      {
          user !== null && <div className="flex justify-center my-5">
            <LogoutButton/>
          </div>
      }
    </div>
  );
};

export default SideMenu;
