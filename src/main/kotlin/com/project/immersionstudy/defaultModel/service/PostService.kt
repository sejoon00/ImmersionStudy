package com.project.immersionstudy.defaultModel.service

import com.project.immersionstudy.defaultModel.data.ExposedPostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    val exposedPostRepository: ExposedPostRepository
) {

}