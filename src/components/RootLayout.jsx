import React from 'react'
import { Outlet } from 'react-router-dom'


//components
import NavigateBar from './NavigateBar'

function RootLayout() {
  return (
    <>
        <NavigateBar />
        <Outlet />
    </>
  )
}

export default RootLayout