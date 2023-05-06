package com.project.k_firesquad

import com.project.k_firesquad.utlies.ProductQuantityUpdateListener
import com.project.k_firesquad.utlies.ProductQuantityUpdater
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProductQuantityUpdaterTest {

    @Mock
    lateinit var listener: ProductQuantityUpdateListener

    lateinit var updater: ProductQuantityUpdater

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        updater = ProductQuantityUpdater(listener)
    }

    @Test
    fun `should call onInvalidQuantity if insertedQty is greater than or equal to productQty`() {
        updater.updateQuantity(10, 20)
        verify(listener).onInvalidQuantity()
        verify(listener, never()).onQuantityUpdated(anyInt())
    }

    @Test
    fun `should call onQuantityUpdated with correct updatedQty if insertedQty is less than productQty`() {
        updater.updateQuantity(10, 5)
        verify(listener).onQuantityUpdated(5)
        verify(listener, never()).onInvalidQuantity()
    }

    @Test
    fun `should return 0 if insertedQty is greater than or equal to productQty`() {
        val updatedQty = updater.updateQuantity(10, 20)
        assertEquals(0, updatedQty)
    }

    @Test
    fun `should return correct updatedQty if insertedQty is less than productQty`() {
        val updatedQty = updater.updateQuantity(10, 5)
        assertEquals(5, updatedQty)
    }
}
