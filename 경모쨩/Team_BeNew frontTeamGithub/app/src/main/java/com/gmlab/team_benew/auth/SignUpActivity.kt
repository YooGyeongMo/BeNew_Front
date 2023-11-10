package com.gmlab.team_benew.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmlab.team_benew.R

class SignUpActivity: AppCompatActivity() {
    private var currentStep = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//        // 첫 번째 프레그먼트로 시작
//        showFragment(SignupStep1Fragment())
//    }
//
//    // 각 단계별로 프레그먼트를 전환하는 함수
//    private fun showFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, fragment)
//            .commit()
//    }
//
//    // 다음 단계로 이동하는 함수
//    fun moveToNextStep() {
//        currentStep++
//        when (currentStep) {
//            2 -> showFragment(SignupStep2Fragment())
//            3 -> showFragment(SignupStep3Fragment())
//            else -> {
//                // 회원가입 완료 또는 처리 로직을 추가
//                // 여기에서 각 프레그먼트에서 수집한 정보를 사용하여 회원가입 로직을 처리할 수 있습니다.
//                // 예를 들어, 네트워크 요청 등을 수행할 수 있습니다.
//                // 그 후, 필요에 따라 MainActivity 등으로 이동하면 됩니다.
//                // 예시: startActivity(Intent(this, MainActivity::class.java))
//            }
//        }
//    }
//
//    // 각 프레그먼트에서 호출하여 이전 단계로 이동하는 함수
//    fun moveToPreviousStep() {
//        if (currentStep > 1) {
//            currentStep--
//            when (currentStep) {
//                1 -> showFragment(SignupStep1Fragment())
//                2 -> showFragment(SignupStep2Fragment())
//            }
//        } else {
//            // 현재가 첫 번째 단계라면 추가적인 처리 로직을 수행할 수 있습니다.
//        }
     }
}