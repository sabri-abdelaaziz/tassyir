package com.wagdev.tassyir.earning_feature.presentation.Earns

import com.wagdev.tassyir.earning_feature.domain.model.Earn

sealed class EarnEvent {
    data class insertEarn(val earn: Earn) : EarnEvent()
    data class deleteEarn(val earn: Earn) : EarnEvent()
    data class updateEarn(val earn: Earn) : EarnEvent()
    data class getEarnById(val id: Int) : EarnEvent()
    object getEarns: EarnEvent()

}