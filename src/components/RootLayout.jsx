import React from 'react'
import { Outlet } from 'react-router-dom'


//components
import NavigateBar from './NavigateBar'
import Footer from './Footer'
function RootLayout() {
  return (
    <>
        <NavigateBar />
        <Outlet />
        <Footer />
    </>
  )
}

export default RootLayout