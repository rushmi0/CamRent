import React from 'react'
import { Outlet } from 'react-router-dom'

//components
import NavigateBar from './NavigateBar'


// Done!
function RootLayout() {
  return (
    <>
        <NavigateBar />
        <Outlet />
    </>
  )
}

export default RootLayout