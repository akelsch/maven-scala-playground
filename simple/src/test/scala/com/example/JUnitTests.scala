package com.example

import org.junit.Assert.assertEquals
import org.junit.Test
import org.scalatestplus.junit.JUnitSuite

class JUnitTests extends JUnitSuite {

  @Test
  def addition(): Unit = {
    assertEquals(2, 1 + 1)
  }
}
