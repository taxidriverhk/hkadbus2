package com.taxidriverhk.hkadbus2.util

import java.math.BigInteger

object IdentifierGenerator {

    @JvmStatic
    fun generateNumericIdentifier(uuid: String): Long {
        val uuidWithoutDashes = uuid.replace("-", "")
        val bigNumber = BigInteger(uuidWithoutDashes, 16)

        val numericIdString = bigNumber.toString()
        val digits = numericIdString.length
        val shorternNumericId = numericIdString.substring(digits - 9, digits)

        return shorternNumericId.toLong()
    }
}
